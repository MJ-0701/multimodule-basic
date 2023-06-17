package com.example.multimodulecore.repository.team

import com.example.multimodulecore.domain.team.QTeam
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Component

@Component
class TeamQueryDslRepository(
    val queryFactory : JPAQueryFactory,
    em : EntityManager
) {

    fun test() {
//        val response = queryFactory.select(QTeam.team).from(QTeam.team).fetch()

        print("동작 확인")
    }
}