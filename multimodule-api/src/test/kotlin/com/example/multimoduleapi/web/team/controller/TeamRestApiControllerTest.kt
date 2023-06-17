package com.example.multimoduleapi.web.team.controller

import com.example.multimodulecore.repository.team.TeamQueryDslRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TeamRestApiControllerTest @Autowired constructor(
    val teamQueryDslRepository: TeamQueryDslRepository
) {



    @Test
    fun `동작_테스트`() {
        teamQueryDslRepository.test()

    }
}


