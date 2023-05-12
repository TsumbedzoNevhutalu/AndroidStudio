package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
//initialize and binding with the api
        etUsername = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

//adding functionality to the login button
        btnLogin.setOnClickListener(){
            login(etUsername as EditText, etPassword as EditText);
        }
    }

//    function for the login button
    fun login(Name:EditText,Pass:EditText) {
        val userName: String = Name.getText().toString().trim()
        val password: String = Pass.getText().toString().trim()

        val call: Call<ResponseBody> = retrofitClient
            .getInstance()
            .api
            .checkUser(user(userName, password))

        if (userName.isEmpty()) {
            Name.setError("Username is required")
            Name.requestFocus()
            return
        } else if (password.isEmpty()) {
            Pass.setError("Password is required")
            Pass.requestFocus()
            return
        }

        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>) {
                var s = ""
                try {
                    s = response.body()!!.string()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if (s == userName) {
// get the userName, with this code it helps the dashboard to get the username/pick up the username
                    val intent = Intent(this@LoginActivity,DashboardActivity::class.java)
                    intent.putExtra("Username",s) //till here
                    Toast.makeText(
                        this@LoginActivity,
                        "Successfully login",
                        Toast.LENGTH_LONG
                    ).show()
                    //directing the app where to go from the recent activity
                    startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                } else {
                    Toast.makeText(this@LoginActivity, "User does not exists!", Toast.LENGTH_LONG)
                        .show()
                }


            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Toast.makeText(this@LoginActivity, t.message, Toast.LENGTH_LONG).show()
            }


        })
    }



}