package com.travelify.travelify.service;

import com.travelify.travelify.entity.Otp;
import com.travelify.travelify.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OtpCleanupService {

    @Autowired
    private OtpRepository otpRepository;

    @Scheduled(fixedRate = 60000)
    public void cleanupExpiredOtps(){
        List<Otp> expiredOtps = otpRepository.findAll().stream()
                .filter(otp -> otp.getExpiryTime().before(new Date()))
                .toList();
        otpRepository.deleteAll(expiredOtps);
    }
}
