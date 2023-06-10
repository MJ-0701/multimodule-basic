package com.example.multimodulebasic.domain.test

import jakarta.persistence.*

@Entity
@Table(name = "test_domain")
data class TestOne(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private val testId : Long,

)
