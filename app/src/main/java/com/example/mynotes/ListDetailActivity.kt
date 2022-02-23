package com.example.mynotes

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.mynotes.models.TaskList
import com.example.mynotes.ui.detail.ListDetailFragment
import com.example.mynotes.ui.detail.ListDetailViewModel
import com.example.mynotes.ui.main.MainViewModel
import com.example.mynotes.databinding.ListDetailActivityBinding
import com.example.mynotes.ui.main.MainViewModelFactory

class ListDetailActivity : AppCompatActivity() {
    //private lateinit var binding: ListDetailActivityBinding
    //private lateinit var viewModel: MainViewModel
    lateinit var sharedPreference: SharedPreferences
    lateinit var list: TaskList
    lateinit var listDetailEdittext: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_detail_activity)

        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        title = list.name

        /*viewModel = ViewModelProvider(this,
            MainViewModelFactory(PreferenceManager.getDefaultSharedPreferences(this))
        )
            .get(MainViewModel::class.java)
        binding = ListDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addTaskButton.setOnClickListener {
            showCreateTaskDialog()
        }
        viewModel.list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY)!!
        title = viewModel.list.name*/

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListDetailFragment.newInstance())
                .commitNow()
        }
    }

    public override fun onPostCreate(savedInstanceState: Bundle?) {
        sharedPreference = getSharedPreferences("", MODE_PRIVATE)
        listDetailEdittext = findViewById(R.id.list_detail_edittext)
        var loadText = sharedPreference.getString(list.name,"")
        listDetailEdittext.setText(loadText)
        super.onPostCreate(savedInstanceState)
    }

    public fun UpdateText() {
        //TEST
        sharedPreference = getSharedPreferences("", MODE_PRIVATE)
        listDetailEdittext = findViewById(R.id.list_detail_edittext)
        var loadText = sharedPreference.getString(list.name,"")
        listDetailEdittext.setText(loadText)

    }

/*    private fun showCreateTaskDialog() {
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT

        AlertDialog.Builder(this)
            .setTitle("What is the task you want to add?")
            .setView(taskEditText)
            .setPositiveButton("Add") { dialog, _ ->
                val task = taskEditText.text.toString()
                viewModel.addTask(task)
                dialog.dismiss()
            }
            .create()
            .show()
    }*/

    override fun onBackPressed() {
        sharedPreference = getSharedPreferences("", MODE_PRIVATE)
        listDetailEdittext = findViewById(R.id.list_detail_edittext)

        var edited = listDetailEdittext.text.toString()

        listDetailEdittext.setText(edited)
        sharedPreference.edit().putString(list.name,edited).apply()

        super.onBackPressed()
    }
}