package fen.code.choicemaker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_vote.*

class VoteActivity : AppCompatActivity() {

    var countPerson: Int = 0
    var countTotal: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vote)

        countPerson = intent.getIntExtra("person", 1)
        countTotal = intent.getIntExtra("total", 1)
        supportActionBar!!.title = countPerson.toString() + " " + countTotal.toString()

        vote_next.setOnClickListener {
            val intent: Intent
            if (countPerson <= 0)
                intent = Intent(this, ResultActivity::class.java)
            else
                intent = Intent(this, VoteActivity::class.java)

            countPerson--
            intent.putExtra("person", countPerson)
            intent.putExtra("total", countTotal)
            startActivity(intent)
        }

        vote_prev.setOnClickListener {
            countPerson++
            if (countPerson >= countTotal) {
                val intent = Intent(this, VoteActivity::class.java)
                intent.putExtra("person", countPerson)
                intent.putExtra("total", countTotal)
                startActivity(intent)
            }
            finish()
        }
    }
}
