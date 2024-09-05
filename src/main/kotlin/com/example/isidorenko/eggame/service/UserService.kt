package com.example.isidorenko.eggame.service

import com.example.isidorenko.eggame.entity.User

interface UserService {

    fun registerUser(name: String): User?

    fun findByName(name: String): User?

    fun save(user: User): User

    fun findAll(): List<User>

}