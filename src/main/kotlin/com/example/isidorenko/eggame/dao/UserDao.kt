package com.example.isidorenko.eggame.dao

import com.example.isidorenko.eggame.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserDao : JpaRepository<User, Long> {

    fun existsByName(name: String): Boolean

    fun findByName(name: String): User?

}
