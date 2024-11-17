package com.ssafy.sandbox.domain.oauth.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.sandbox.domain.oauth.entity.OauthInfo;
import com.ssafy.sandbox.domain.oauth.repository.OauthRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.ssafy.sandbox.domain.oauth.entity.QOauthInfo.oauthInfo;

@Repository
public class OauthRepositoryImpl implements OauthRepository {

    private final EntityManager entityManager;

    private final JPAQueryFactory jpaQueryFactory;

    public OauthRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }


    @Override
    public void save(OauthInfo oauthInfo) {

        entityManager.persist(oauthInfo);
    }

    @Override
    public Optional<OauthInfo> findByRefreshToken(String refreshToken) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(oauthInfo)
                        .where(oauthInfo.refreshToken.eq(refreshToken))
                        .fetchOne()
        );
    }


}
