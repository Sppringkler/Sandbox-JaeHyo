package com.ssafy.sandbox.domain.user.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sandbox.domain.user.entity.User;
import com.ssafy.sandbox.domain.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import static com.ssafy.sandbox.domain.user.entity.QUser.user;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final EntityManager entityManager;

    private final JPAQueryFactory jpaQueryFactory;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findById(String kakaoId) {
        return jpaQueryFactory
                .selectFrom(user)
                .where(user.kakaoId.eq(kakaoId))
                .fetchOne();
    }
}
