package com.example.myquizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var currentPosition = 1
    private lateinit var questionsList: ArrayList<Question>
    private var selectedOptionPosition: Int = 0
    private var userName: String? = null
    private var correctAnswer: Int = 0

    private lateinit var tvQuestion: TextView
    private lateinit var ivFlag: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvProgress: TextView

    private lateinit var tvOptionOne: TextView
    private lateinit var tvOptionTwo: TextView
    private lateinit var tvOptionThree: TextView
    private lateinit var tvOptionFour: TextView

    private lateinit var btnSubmit: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        userName = intent.getStringExtra(Constants.USER_NAME)

        tvQuestion = findViewById(R.id.tv_question)
        ivFlag = findViewById(R.id.iv_flag)
        progressBar = findViewById(R.id.progressBar)
        tvProgress = findViewById(R.id.tv_progress)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        tvOptionOne.setOnClickListener(this)
        tvOptionTwo.setOnClickListener(this)
        tvOptionThree.setOnClickListener(this)
        tvOptionFour.setOnClickListener(this)
        btnSubmit.setOnClickListener(this)

        questionsList = Constants.getQuestions()

        setQuestion()
        defaultOptionsView()

    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tvOptionOne.let {
            options.add(0, it)
        }
        tvOptionTwo.let {
            options.add(1, it)
        }
        tvOptionThree.let {
            options.add(2, it)
        }
        tvOptionFour.let {
            options.add(3, it)
        }

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.tv_background)

        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()

        selectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selected_border_bg)


    }

    private fun setQuestion() {
        defaultOptionsView()

        val currentQuestion = questionsList[currentPosition - 1]
        progressBar.progress = currentPosition
        tvProgress.text = "$currentPosition/${progressBar.max}"
        tvQuestion.text = currentQuestion.question
        tvOptionOne.text = currentQuestion.optionOne
        tvOptionTwo.text = currentQuestion.optionTwo
        tvOptionThree.text = currentQuestion.optionThree
        tvOptionFour.text = currentQuestion.optionFour
        ivFlag.setImageResource(currentQuestion.image)

        if(currentPosition == questionsList.size){
            btnSubmit.text = "FINISHED"
        }else{
            btnSubmit.text = "SUBMIT"
        }
    }

    private fun answerView(answer: Int, drawableView: Int){
        when(answer){
            1-> tvOptionOne.background = ContextCompat.getDrawable(this,drawableView)
            2-> tvOptionTwo.background = ContextCompat.getDrawable(this, drawableView)
            3-> tvOptionThree.background = ContextCompat.getDrawable(this, drawableView)
            4-> tvOptionFour.background = ContextCompat.getDrawable(this, drawableView)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_option_one ->{
                tvOptionOne.let {
                    selectedOptionView(it,1)
                }
            }
            R.id.tv_option_two ->{
                tvOptionTwo.let {
                    selectedOptionView(it,2)
                }
            }
            R.id.tv_option_three ->{
                tvOptionThree.let {
                    selectedOptionView(it,3)
                }
            }
            R.id.tv_option_four ->{
                tvOptionFour.let {
                    selectedOptionView(it,4)
                }
            }
            R.id.btn_submit ->{
                btnSubmit.let {
                    if(selectedOptionPosition == 0){
                        currentPosition++

                        when{
                            currentPosition <= questionsList.size ->{
                                setQuestion()
                            }
                            else->{
                                val intent = Intent(this, ResultActivity::class.java)
                                intent.putExtra(Constants.USER_NAME, userName)
                                intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswer)
                                intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
                                startActivity(intent)
                                finish()
                            }
                        }
                    }else{
                        val question = questionsList.get(currentPosition -1)

                        if(question.correctAnswer != selectedOptionPosition){
                            answerView(selectedOptionPosition, R.drawable.wrong_option)
                        }else{
                            correctAnswer++
                        }
                        answerView(question.correctAnswer, R.drawable.correct_option) //by default

                        if(currentPosition == questionsList.size){
                            btnSubmit.text = "FINISHED"
                        }else{
                            btnSubmit.text = "NEXT QUESTION"
                        }
                    }
                    selectedOptionPosition = 0
                }
            }
        }
    }
}