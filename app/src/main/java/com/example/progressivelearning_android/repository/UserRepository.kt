package com.example.progressivelearning_android.repository

import com.example.progressivelearning_android.model.User
import kotlinx.android.synthetic.main.fragment_login.*

class UserRepository {

   private var listOfUsers: ArrayList<User> = arrayListOf(
        User("mohamed_ben.ali@hotmail.com", "dummy1234")
    )

    fun login(email: String, password: String): Boolean {
//        val user = User(email, password)
//        return getUser(user) != null
        return true
    }

    fun signUp(email: String, password: String, confirmPassword: String): Boolean {
        val user = User(email, password)
        if(password == confirmPassword && getUser(user) == null) {
            listOfUsers.add(user)
            return true
        } else return false
    }

    private fun getUser(user: User): User? {
        var foundUser: User? = null
        for (u in listOfUsers) {
            if(user.username == u.username && user.password == u.password) {
                foundUser = user
            }
        }
        return foundUser
    }

}