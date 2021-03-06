package com.csci448.connorkoch.connorkoch_a1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateQuestion()
        true_button.setOnClickListener { checkAnswer(true) }
        false_button.setOnClickListener { checkAnswer(false) }
        prev_button.setOnClickListener { moveToQuestion(-1) }
        next_button.setOnClickListener { moveToQuestion(1) }
    }

    private fun moveToQuestion(mv: Int){
        if(mv >= 0) QuizMaster.moveToNextQuestion()
        else QuizMaster.moveToPreviousQuestion()

        if(QuizMaster.getCurrentQuestionIndex() == 4) {
            tf_layout.visibility = View.INVISIBLE
            mc_layout.visibility = View.VISIBLE
            fr_layout.visibility = View.INVISIBLE
            //setContentView(R.layout.activity_mc)
//            val intent = Intent(this, activity_mc::class.java)
//            intent.putExtra("key", value)
//            startActivity(intent)
        }
        else if(QuizMaster.getCurrentQuestionIndex() == 5) {
            tf_layout.visibility = View.INVISIBLE
            mc_layout.visibility = View.INVISIBLE
            fr_layout.visibility = View.VISIBLE
        }

        updateQuestion()
    }


    private fun updateQuestion(){
        setCurrentScoreText()
        question_text_view.text = resources.getString( QuizMaster.getCurrentQuestionTextId() )
    }

    private fun setCurrentScoreText() {
        score_text_view.text = QuizMaster.currentScore.toString()
    }

    private fun checkAnswer(ans: Boolean){
        if(QuizMaster.isAnswerCorrect(ans)){
            Toast.makeText(baseContext, R.string.correct_toast, Toast.LENGTH_SHORT).show()
            setCurrentScoreText()
        }
        else Toast.makeText(baseContext,
            R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
    }
}
