package com.example.isidorenko.eggame.entity

import jakarta.persistence.*

@Entity
@Table(name = "users")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = GENERATOR_NAME)
    @SequenceGenerator(name = GENERATOR_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    var id: Long? = null

    @Column(nullable = false)
    var name: String? = null

    @Column(nullable = false)
    var score: Long? = null

    companion object {
        const val GENERATOR_NAME = "users_seq"
        const val SEQUENCE_NAME = "seq_users"
    }

}
