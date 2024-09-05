package com.example.isidorenko.eggame.controller

import com.example.isidorenko.eggame.dto.RegisterInfo
import com.example.isidorenko.eggame.dto.ScoreInfo
import com.example.isidorenko.eggame.entity.User
import com.example.isidorenko.eggame.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController @Autowired constructor(
    private val userService: UserService
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody request: RegisterInfo): ResponseEntity<Any> {

        if (request.name.isBlank())
            return ResponseEntity("Имя пользователя не может быть пустым", HttpStatus.BAD_REQUEST)

        val user = userService.registerUser(request.name)
            ?: return ResponseEntity("Пользователь с таким именем уже существует", HttpStatus.CONFLICT)

        return ResponseEntity.status(HttpStatus.OK).body(toMap(user))

    }

    @GetMapping("/score")
    fun getUserScore(@RequestParam(name = "name") name: String): ResponseEntity<Any> {
        val user = userService.findByName(name)
            ?: return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь с именем $name не найден")

        return ResponseEntity.status(HttpStatus.OK).body(toMap(user))
    }

    @PutMapping("/score")
    fun saveUserScore(@RequestBody request: ScoreInfo): ResponseEntity<Any> {
        val user = userService.findByName(request.name)
            ?: return ResponseEntity("Пользователь с именем ${request.name} не найден", HttpStatus.NOT_FOUND)

        user.score = request.score
        val savedUser = userService.save(user)

        return ResponseEntity.status(HttpStatus.OK).body(toMap(savedUser)
        )

    }

    @GetMapping("/leaderboard")
    fun getLeaderboard(): ResponseEntity<Any> {
        val users = userService.findAll()
        if (users.isEmpty()) return ResponseEntity(HttpStatus.NO_CONTENT)

        val sortedUsers = users.sortedByDescending { it.score }
        val usersMap = sortedUsers.map { toMap(it) }
        return ResponseEntity.status(HttpStatus.OK).body(usersMap)
    }

    private fun toMap(user: User): Map<String, String> {
        return mapOf(
            "name" to user.name.toString(),
            "score" to user.score.toString()
        )
    }

}