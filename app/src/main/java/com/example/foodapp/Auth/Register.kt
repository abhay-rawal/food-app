package com.example.foodapp.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodapp.DataClass.User
import com.example.foodapp.databinding.ActivityRegisterBinding
import com.google.gson.Gson

class Register : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var intent = Intent(this, Login::class.java);
        binding.register.setOnClickListener{
            val gson = Gson()
            val name = binding.username.text.toString()
            val password = binding.password.text.toString()
            val phone = binding.phoneNumber.text.toString()
            val email = binding.email.text.toString()

            val ob = User(name, password, phone, email)
            val first = gson.toJson(ob)

            val spf = getSharedPreferences("user", MODE_PRIVATE)
            val editor = spf.edit()
            editor.putString("user", first)
            editor.apply()

            runOnUiThread{
                Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            }

            startActivity(intent)
            finish();
        }

    }
}