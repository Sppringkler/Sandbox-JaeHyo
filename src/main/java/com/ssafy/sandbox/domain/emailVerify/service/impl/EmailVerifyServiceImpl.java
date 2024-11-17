package com.ssafy.sandbox.domain.emailVerify.service.impl;

import com.ssafy.sandbox.controller.request.EmailVerifyReq;
import com.ssafy.sandbox.controller.request.SendEmailReq;
import com.ssafy.sandbox.domain.emailVerify.entity.EmailVerify;
import com.ssafy.sandbox.domain.emailVerify.repository.EmailVerifyRepository;
import com.ssafy.sandbox.domain.emailVerify.service.EmailVerifyService;
import com.ssafy.sandbox.global.exception.BusinessException;
import com.ssafy.sandbox.global.exception.EmailVerifyNotFoundException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

import static java.time.LocalDateTime.now;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailVerifyServiceImpl implements EmailVerifyService {

    private final EmailVerifyRepository evRepository;

    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "hyoseung.lee2000@gmail.com";

    @Override
    public void sendEmailVerifyCode(SendEmailReq req) {
        String email = req.getEmail();
        // 인증번호 생성
        String verifyCode = createNumber();
        //만료기한 설정
        LocalDateTime expireTime = LocalDateTime.now().plusMinutes(5);

        EmailVerify emailVerify = EmailVerify.builder()
                .email(email)
                .verifyCode(verifyCode)
                .expireTime(expireTime)
                .build();

        evRepository.saveVerifyCode(emailVerify);

        //이메일 텍스트 설정
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, email);
            message.setSubject("SANDBOX 이메일 인증");
            String body = "";
            body += "<h3>요청하신 인증 번호입니다.</h3>";
            body += "<h1>" + verifyCode + "</h1>";
            body += "<h3>감사합니다.</h3>";
            message.setText(body, "UTF-8", "html");
        } catch (MessagingException e) {
            throw new BusinessException(e.getMessage());
        }

        //이메일 발송
        javaMailSender.send(message);
    }

    @Override
    public void verifyEmailCode(EmailVerifyReq req) {
        String email = req.getEmail();
        String authentication = req.getAuthentication();

        EmailVerify emailVerify = evRepository.findByEmail(email)
                .orElseThrow(() -> new EmailVerifyNotFoundException("정상적이지 않은 요청입니다."));

        if(now().isAfter(emailVerify.getExpireTime())) {
            throw new BusinessException("인증시간이 만료되었습니다.");
        }

        if(!authentication.equals(emailVerify.getVerifyCode())) {
            throw new BusinessException("코드가 일치하지 않습니다.");
        }

        evRepository.deleteByEmail(email);
    }

    public String createNumber() {
        try {
            Random random = SecureRandom.getInstanceStrong();
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < 6; i++) {
                builder.append(random.nextInt(10));
            }

            return builder.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
