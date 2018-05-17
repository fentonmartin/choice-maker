package fen.code.choicemaker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_person.*

class PersonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        person_button.setOnClickListener {
            val intent = Intent(this, ChoiceActivity::class.java)
            intent.putExtra("person", person_text.text.toString().toInt())
            intent.putExtra("total", person_text.text.toString().toInt())

            startActivity(intent)
        }
    }
}
