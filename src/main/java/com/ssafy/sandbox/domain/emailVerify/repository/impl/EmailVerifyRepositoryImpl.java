package com.ssafy.sandbox.domain.emailVerify.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sandbox.domain.emailVerify.entity.EmailVerify;
import com.ssafy.sandbox.domain.emailVerify.repository.EmailVerifyRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.ssafy.sandbox.domain.emailVerify.entity.QEmailVerify.emailVerify;

@Repository
public class EmailVerifyRepositoryImpl implements EmailVerifyRepository {

    private final EntityManager entityManager;

    private final JPAQueryFactory jpaQueryFactory;

    public EmailVerifyRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public void saveVerifyCode(EmailVerify emailVerify) {
        entityManager.persist(emailVerify);
    }

    @Override
    public Optional<EmailVerify> findByEmail(String email) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(emailVerify)
                        .where(emailVerify.email.eq(email))
                        .orderBy(emailVerify.expireTime.desc())
                        .fetchOne()
        );
    }

    @Override
    public void deleteByEmail(String email) {
        jpaQueryFactory
                .delete(emailVerify)
                .where(emailVerify.email.eq(email))
                .execute();
    }
}
