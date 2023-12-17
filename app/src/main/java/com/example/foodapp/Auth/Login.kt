package com.example.foodapp.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.foodapp.MainActivity
import com.example.foodapp.DataClass.User
import com.example.foodapp.databinding.ActivityLoginBinding
import com.google.gson.Gson

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.login.setOnClickListener{
            var intent =  Intent(this, MainActivity::class.java);

            val spf = getSharedPreferences("user", MODE_PRIVATE)
            val res = spf.getString("user", "")
            val opt = Gson().fromJson(res, User::class.java)

            if(opt!=null && (opt.userName == binding.username.text.toString() && opt.password == binding.password.text.toString())){
                startActivity(intent)
                finish();
            }
            else
            {
                runOnUiThread{
                   Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }

        }
        binding.register.setOnClickListener{
            var intent =  Intent(this, Register::class.java);
            startActivity(intent)
            finish();
        }
    }
}