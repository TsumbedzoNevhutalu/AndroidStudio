package com.example.myapplication


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class RegisterActivity : AppCompatActivity() {
    //  declaring variables from the user activity and a button
    lateinit var etUsername: EditText
    lateinit var etPassword: EditText
    lateinit var btnRegister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

//  binding through initializing the activity and the xml the username and password  attribute with the xml since it is finding by id
        etUsername = findViewById(R.id.etRUserName)
        etPassword = findViewById(R.id.etRPassword)
        btnRegister = findViewById(R.id.btnRegister)

//routing to the Login activity with the function onclickListener
        this.findViewById<TextView>(R.id.tvLoginLink).setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))//routing to the Login activity since we are still in the register activity
        }


//activation of the button with the onclick function
        btnRegister.setOnClickListener {
//tell the app what to do
            val Name=etUsername.getText().toString()
           registerUser();
           // Toast.makeText(this, "Button working", Toast.LENGTH_LONG)

        }
    }

    private fun registerUser() {
        val userName: String = etUsername.getText().toString().trim()
        val password: String = etPassword.getText().toString().trim()

        if (userName.isEmpty()) {
            etUsername.setError("Username is required")
            etUsername.requestFocus()
            return
        } else if (password.isEmpty()) {
            etPassword.setError("Password is required")
            etPassword.requestFocus()
            return
        }
        val call: Call<ResponseBody> = retrofitClient
            .getInstance() //calling the dependecies
            .api  //binding the api
            .createUser(user(userName, password))
        call.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>) {
                var s = ""
                try {
                    s = response.body()!!.string()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if (s == "SUCCESS") {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Successfully registered. Please login",
                        Toast.LENGTH_LONG
                    ).show()
                  startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                } else {
                    Toast.makeText(this@RegisterActivity, "User already exists!", Toast.LENGTH_LONG)
                        .show()
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
 Toast.makeText(this@RegisterActivity, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}












