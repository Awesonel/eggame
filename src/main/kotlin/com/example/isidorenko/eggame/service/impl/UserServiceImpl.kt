package com.example.isidorenko.eggame.service.impl

import com.example.isidorenko.eggame.dao.UserDao
import com.example.isidorenko.eggame.entity.User
import com.example.isidorenko.eggame.service.UserService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userDao: UserDao
) : UserService{

    @Transactional
    override fun registerUser(name: String): User? {

        if (name.isBlank() || userDao.existsByName(name)) {
            return null
        }

        val newUser = User()
        newUser.name = name
        newUser.score = 0
        val savedUser = userDao.save(newUser)

        return savedUser
    }

    override fun findByName(name: String): User? {
        return userDao.findByName(name)
    }

    override fun save(user: User): User {
        return userDao.save(user)
    }

    override fun findAll(): List<User> {
        return userDao.findAll()
    }

}