package fen.code.choicemaker

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_choice.*

class ChoiceActivity : AppCompatActivity() {
    var countPerson:Int = 0
    var countTotal:Int = 0
    private lateinit var listView: ListView
    private val choices: MutableList<String> = mutableListOf(
            "Ayam Geprek", "Ayam Bakar", "Nasi Padang", "Nasi Goreng")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        countPerson = intent.getIntExtra("person", 1)
        countTotal = intent.getIntExtra("total", 1)

        listView = findViewById(R.id.choice_list)
        showList()

        choice_add.setOnClickListener {
            showCreateCategoryDialog()
        }

        choice_next.setOnClickListener {
            val intent = Intent(this, VoteActivity::class.java)
            intent.putExtra("person", countPerson-1)
            intent.putExtra("total", countTotal)
            startActivity(intent)
        }
    }

    fun showList() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, choices)
        listView.adapter = adapter
    }

    fun showCreateCategoryDialog() {
        val context = this
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Add new choice")

        val view = layoutInflater.inflate(R.layout.dialog_add, null)

        val categoryEditText = view.findViewById(R.id.categoryEditText) as EditText

        builder.setView(view);

        // set up the ok button
        builder.setPositiveButton(android.R.string.ok) { dialog, p1 ->
            val newCategory = categoryEditText.text
            var isValid = true
            if (newCategory.isBlank()) {
                categoryEditText.error = getString(R.string.validation_empty)
                isValid = false
            }

            if (isValid) {
                choices.add(newCategory.toString())
                choices.shuffle()
                showList()
            }

            if (isValid) {
                dialog.dismiss()
            }
        }

        builder.setNegativeButton(android.R.string.cancel) { dialog, p1 ->
            dialog.cancel()
        }

        builder.show()
    }
}
