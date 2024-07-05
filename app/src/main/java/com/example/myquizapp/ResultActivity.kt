package com.example.myquizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvUserName: TextView = findViewById(R.id.tv_user_name)
        val tvUserScore: TextView= findViewById(R.id.tv_user_score)
        val btnFinish: Button= findViewById(R.id.btn_finish)
        val tvResultMessage: TextView = findViewById(R.id.tv_result_message)


        tvUserName.text = intent.getStringExtra(Constants.USER_NAME)
        val totalQuestion = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswer = intent.getIntExtra(Constants.CORRECT_ANSWERs, 0)
        tvUserScore.text = "Your score is $correctAnswer out of $totalQuestion"

        if(correctAnswer <= 4){
            tvResultMessage.text = "Don't give up, let's try again!"
        }

        btnFinish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }



    }
}