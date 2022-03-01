package com.example.saving_data_shared_preferences

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var et_name: EditText
    lateinit var et_surname: EditText
    lateinit var et_age: EditText
    lateinit var et_money: EditText
    lateinit var et_email: EditText
    lateinit var et_password: EditText
    lateinit var et_confirm_password: EditText
    lateinit var cb_isMarried: CheckBox
    lateinit var btn_save: Button
    lateinit var btn_load: Button
    lateinit var btn_send: Button
    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        et_name = findViewById(R.id.et_name)
        et_surname = findViewById(R.id.et_surname)
        et_age = findViewById(R.id.et_age)
        et_money = findViewById(R.id.et_money)
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)
        et_confirm_password = findViewById(R.id.et_confirm_password)
        cb_isMarried = findViewById(R.id.isMarried)
        btn_save = findViewById(R.id.btn_sign_up)
        btn_load = findViewById(R.id.btn_load)
        btn_send = findViewById(R.id.btn_open_details)

        sharedPref = getSharedPreferences("Details", MODE_PRIVATE)


        btn_save.setOnClickListener {
            if (et_password.text.toString().equals(et_confirm_password.text.toString())) {
                saveData()
            } else {
                Toast.makeText(this, "Password is not the same , try again", Toast.LENGTH_LONG)
                    .show()
            }
        }

        btn_load.setOnClickListener {
            loadData()
        }

        btn_send.setOnClickListener {
            if (et_password.text.toString().equals(et_confirm_password.text.toString())) {
                sendDetails()
            } else {
                Toast.makeText(this, "Password is not the same , try again", Toast.LENGTH_LONG)
                    .show()
            }

        }

    }

    private fun sendDetails() {
        val user = User()
        user.name = et_name.text.toString()
        user.surname = et_surname.text.toString()
        user.age = et_age.text.toString().toInt()
        user.money = et_money.text.toString().toFloat()
        user.email = et_email.text.toString()
        user.password = et_password.text.toString()
        user.isMarried = cb_isMarried.isChecked

        val gson = Gson()
        val json = gson.toJson(user)
        val editor = sharedPref.edit()
        editor.putString("User",json)
        editor.apply()


        val gson1 = Gson()
        val json1 = sharedPref.getString("User","No data saved yet")
        val user_details = gson1.fromJson(json1,User::class.java)

        val intent = Intent(this,DetailsActivity::class.java)
        Log.d("@@",user_details.toString())
        intent.putExtra("User",user_details)
        startActivity(intent)
    }

    private fun loadData() {
        val name = sharedPref.getString("name", null)
        val surname = sharedPref.getString("surname", null)
        val age = sharedPref.getInt("age", 0)
        val money = sharedPref.getFloat("money", 0f)
        val email = sharedPref.getString("email", null)
        val password = sharedPref.getString("password", null)
        val isMarried = sharedPref.getBoolean("isMarried", false)

        et_name.setText(name)
        et_surname.setText(surname)
        et_age.setText(age.toString())
        et_money.setText(money.toString())
        et_email.setText(email)
        et_password.setText(password)
        cb_isMarried.isChecked = isMarried
    }

    private fun saveData() {
        val name = et_name.text.toString()
        val surname = et_surname.text.toString()
        val age = et_age.text.toString().toInt()
        val money = et_money.text.toString().toFloat()
        val email = et_email.text.toString()
        val password = et_password.text.toString()
        val isMarried = cb_isMarried.isChecked

        val editor = sharedPref.edit()
        editor.apply {
            putString("name", name)
            putString("surname", surname)
            putInt("age", age)
            putFloat("money", money)
            putString("email", email)
            putString("password", password)
            putBoolean("isMarried", isMarried)
            apply()
        }


    }
}


