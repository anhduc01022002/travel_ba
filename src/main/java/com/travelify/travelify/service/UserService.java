package com.travelify.travelify.service;

import com.travelify.travelify.dto.request.SignUpRequest;
import com.travelify.travelify.dto.request.UserUpdateRequest;
import com.travelify.travelify.dto.response.UserResponse;
import com.travelify.travelify.entity.Otp;
import com.travelify.travelify.entity.User;
import com.travelify.travelify.enums.Role;
import com.travelify.travelify.exception.AppException;
import com.travelify.travelify.exception.ErrorCode;
import com.travelify.travelify.mapper.UserMapper;
import com.travelify.travelify.repository.OtpRepository;
import com.travelify.travelify.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    OtpRepository otpRepository;
    JavaMailSender javaMailSender;

    public User signUpUser(SignUpRequest request){

        if (userRepository.existsByEmail(request.getEmail()))
            throw new AppException(ErrorCode.USER_EXISTED);

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmailVerified(false);
        user.setEnabled(false);

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);


        user = userRepository.save(user);

        String otp = generateOtp();
        String otpHash = passwordEncoder.encode(otp);
        Otp otpEntity = Otp.builder()
                .email(user.getEmail())
                .otpHash(otpHash)
                .expiryTime(Date.from(Instant.now().plus(5, ChronoUnit.MINUTES)))
                .build();
        otpRepository.save(otpEntity);

        sendOtpEmail(user.getEmail(), otp);

        return user;
    }

    private String generateOtp(){
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    public void sendOtpEmail(String email, String otp){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Mã xác minh tài khoản");
        message.setText("Mã xác minh của bạn là: " + otp + ". Mã này sẽ hết hạn sau 5 phút.");
        javaMailSender.send(message);
    }

    public boolean verifyOtp(String email, String code){
        Otp otp = otpRepository.findByEmail(email);
        if (otp == null){
            return false;
        }

        if (otp.getExpiryTime().before(new Date())) {
            otpRepository.delete(otp);
            return false;
        }

        boolean isMatch = passwordEncoder.matches(code, otp.getOtpHash());
        if (isMatch) {
            User user = userRepository.findByEmail(email).orElseThrow(
                    () -> new AppException(ErrorCode.USER_NOT_EXISTED));
            user.setEnabled(true);
            user.setEmailVerified(true);
            userRepository.save(user);
            otpRepository.delete(otp);
        }
        return isMatch;
    }

    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByEmail(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }


    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUsers(){
        log.info("In method get Users");
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.email == authentication.name")
    public UserResponse getUser(String id){
        log.info("In method get user by Id");
        return userMapper.toUserResponse(userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)
        ));
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));

        userMapper.updateUser(user, request);

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
