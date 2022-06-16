package ru.netology.pusher

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import java.io.FileInputStream

fun main() {
    println(token)

    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(FileInputStream("fcm.json")))
        .build()

    FirebaseApp.initializeApp(options)

    /*val message = Message.builder()
        .putData("action", "LIKE1")
        .putData("content", """{
          "userId": 1,
          "userName": "Vasiliy",
          "postId": 2,
          "postAuthor": "Netology"
        }""".trimIndent())
        .setToken(token)
        .build()*/

    val message = Message.builder()
        .putData("action", "NEWPOST")
        .putData("content", """{
          "userName": "Vasiliy",
          "content": "Вам необходимо проанализировать, что произойдёт, если в приложение придёт Notification, у которого поле action не соответствует ни одному значению из Enum'а Action"
        }""".trimIndent())
        .setToken(token)
        .build()

    FirebaseMessaging.getInstance().send(message)
}
