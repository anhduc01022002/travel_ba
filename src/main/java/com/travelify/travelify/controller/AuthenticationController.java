package com.travelify.travelify.controller;

import com.nimbusds.jose.JOSEException;
import com.travelify.travelify.dto.request.*;
import com.travelify.travelify.dto.response.AuthenticationResponse;
import com.travelify.travelify.dto.response.IntrospectResponse;
import com.travelify.travelify.exception.ErrorCode;
import com.travelify.travelify.service.AuthenticationService;
import com.travelify.travelify.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    UserService userService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> authenticate(@RequestBody IntrospectRequest request)
            throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request)
            throws ParseException, JOSEException {
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .build();
    }

    @PostMapping("/verify-code")
    ApiResponse<String> verifyCode(@RequestBody VerifyCodeRequest request){
        boolean isVerified = userService.verifyOtp(request.getEmail(), request.getCode());
        if (isVerified) {
            return ApiResponse.<String>builder()
                    .message("Email has been verified")
                    .build();
        } else {
            return ApiResponse.<String>builder()
                    .code(ErrorCode.INVALID_OTP.getCode())
                    .message("OTP is invalid")
                    .build();
        }
    }
}
