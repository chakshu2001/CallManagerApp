package com.example.callmanagerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.concurrent.futures.CallbackToFutureAdapter
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {
    lateinit var databaseReference:DatabaseReference

companion object{
    const val KEY1="com.example.callmanagerapp.name"
    const val KEY2="com.example.callmanagerapp.mail"
    const val KEY3="com.example.callmanagerapp.ID"
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()
        val ettext=findViewById<TextInputEditText>(R.id.textSignIN)
        val button=findViewById<Button>(R.id.btnSignIn)


        button.setOnClickListener {
            val text=ettext.text.toString()
           if(text.isNotEmpty()){
               val text=ettext.text.toString()
            readData(text)
           }
            else{
           Toast.makeText(this,"Please enetr your Unique ID",Toast.LENGTH_SHORT).show()
           }

        }


    }

    private fun readData(text: String) {
      databaseReference=FirebaseDatabase.getInstance().getReference("Users")
      databaseReference.child(text).get().addOnSuccessListener {
      if(it.exists()){
        val email=it.child("email").value.toString()
          val name=it.child("name").value.toString()
          val uniqueId=it.child("uniqueId").value.toString()
          val intentAddContact= Intent(this,AddContact::class.java)
          intentAddContact.putExtra(KEY1,name)
          intentAddContact.putExtra(KEY2,email)
          intentAddContact.putExtra(KEY3,uniqueId)

          startActivity(intentAddContact)
          }
          else{
           Toast.makeText(this,"User is not exist",Toast.LENGTH_SHORT).show()
      }
      }.addOnFailureListener {
          Toast.makeText(this,"Failed, Error in Database", Toast.LENGTH_SHORT).show()
      }

    }
}