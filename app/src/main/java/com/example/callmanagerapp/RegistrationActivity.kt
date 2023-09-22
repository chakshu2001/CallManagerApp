package com.example.callmanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.provider.ContactsContract.Intents
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.w3c.dom.Text
import kotlin.math.sign

class RegistrationActivity : AppCompatActivity() {
    lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        supportActionBar?.hide()
        val signUpButton=findViewById<Button>(R.id.button)
        val etName=findViewById<TextInputEditText>(R.id.Name)
        val etMail=findViewById<TextInputEditText>(R.id.Email)
        val userId=findViewById<TextInputEditText>(R.id.piD)
        signUpButton.setOnClickListener {
            val name=etName.text.toString()
             val mail=etMail.text.toString()
            val userid=userId.text.toString()


            val user=User(name,mail,userid)
            database=FirebaseDatabase.getInstance().getReference("Users")
            database.child(userid).setValue(user).addOnSuccessListener {
                etName.text?.clear()
                etMail.text?.clear()
               userId.text?.clear()


                Toast.makeText(this,"USer registerd",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener  {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }


        }
        var text=findViewById<TextView>(R.id.tvSignIn)
        text.setOnClickListener {
         val openSignIn=Intent(this, SignInActivity::class.java)
            startActivity(openSignIn)
        }
    }
}