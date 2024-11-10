package com.ssafy.sandbox.domain.emailauthinfo.service.impl;

import com.ssafy.sandbox.domain.emailauthinfo.entity.EmailAuthInfo;
import com.ssafy.sandbox.domain.emailauthinfo.repository.EmailAuthInfoRepository;
import com.ssafy.sandbox.domain.emailauthinfo.service.EmailAuthInfoService;
import com.ssafy.sandbox.global.exception.ExpiredAuthenticationException;
import com.ssafy.sandbox.global.exception.MailSendFailException;
import com.ssafy.sandbox.global.exception.NotCorrectAuthenticationException;
import com.ssafy.sandbox.global.exception.NotFoundEmailAuthInfoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailAuthInfoServiceImpl implements EmailAuthInfoService {

    private final EmailAuthInfoRepository emailAuthInfoRepository;

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String adminEmail;

    @Override
    @Transactional
    public void sendEmailAuthCode(String email) {
        emailAuthInfoRepository.findByEmail(email)
                .ifPresent(emailAuth -> emailAuthInfoRepository.deleteByEmail(emailAuth.getEmail()));

        Random random = new Random();
        String authCode = Integer.toString(random.nextInt(888888) + 111111);
        LocalDateTime expiredTime = now().plusMinutes(5);

        EmailAuthInfo emailAuthInfo = EmailAuthInfo.builder()
                .authCode(authCode)
                .email(email)
                .expiredAt(expiredTime)
                .build();

        emailAuthInfoRepository.save(emailAuthInfo);

        String subject = "[SSAFY SANDBOX] 이메일 인증";
        String content = String.format("인증번호는 %s 입니다.", authCode);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(email);
        simpleMailMessage.setFrom(adminEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            throw new MailSendFailException("이메일 전송에 실패했습니다.");
        }
    }

    @Override
    @Transactional
    public void authenticate(String email, String authentication) {
        EmailAuthInfo emailAuthInfo = emailAuthInfoRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundEmailAuthInfoException("해당 이메일로 전송된 인증번호가 없습니다."));

        if (now().isAfter(emailAuthInfo.getExpiredAt())) {
            throw new NotCorrectAuthenticationException("만료된 인증입니다.");
        }
        if (!Objects.equals(authentication, emailAuthInfo.getAuthCode())) {
            throw new ExpiredAuthenticationException("인증번호가 일치하지 않습니다.");
        }

        emailAuthInfoRepository.deleteByEmail(email);
    }
}
