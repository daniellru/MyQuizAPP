package com.example.myquizapp

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions(): ArrayList<Question>{
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1, "Which country does this flag belong to?",
            R.drawable.argentina, "Argetina","Armenia",
            "Russia","Japan", 1
        )

        val que2 = Question(
            2, "Which country does this flag belong to?",
            R.drawable.angola, "Venezuela","Armenia",
            "Australia","Angola", 4
        )

        val que3 = Question(
            3, "Which country does this flag belong to?",
            R.drawable.australia, "New Zealand","Australia",
            "UK","France", 2
        )

        val que4 = Question(
            4, "Which country does this flag belong to?",
            R.drawable.rpdc, "Cuba","Chile",
            "North Korea","China", 3
        )

        val que5 = Question(
            5, "Which country does this flag belong to?",
            R.drawable.brazil, "Colombia","Laos",
            "Congo","Brazil", 4
        )

        val que6 = Question(
            6, "Which country does this flag belong to?",
            R.drawable.canada_svgrepo_com, "Ukraine","Canada",
            "Egypt","Portugal", 2
        )

        val que7 = Question(
            7, "Which country does this flag belong to?",
            R.drawable.lebanon, "Lebanon","Estonia",
            "Palestine","South Africa", 1
        )

        val que8 = Question(
            8, "Which country does this flag belong to?",
            R.drawable.china, "Spain","USA",
            "China","South Korea", 3
        )

        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)
        questionsList.add(que5)
        questionsList.add(que6)
        questionsList.add(que7)
        questionsList.add(que8)

        return questionsList

    }
}