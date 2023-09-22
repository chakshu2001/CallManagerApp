package com.example.callmanagerapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddContact : AppCompatActivity() {
    lateinit var dialog:Dialog
    private lateinit var database:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)
        val userName=findViewById<TextInputEditText>(R.id.userNamE)
        val userPass=findViewById<TextInputEditText>(R.id.userEmailAdd)
        val button=findViewById<Button>(R.id.button2)
        val contNum=findViewById<TextInputEditText>(R.id.contact)

        dialog=Dialog(this)
        dialog.setContentView(R.layout.custom_dialogue)
        dialog.window?.setBackgroundDrawable(getDrawable(R.drawable.bg_alert_box_background))
        var btnGd=dialog.findViewById<Button>(R.id.btnGood)
        var btnFeedbck=dialog.findViewById<Button>(R.id.btnFeedback)
        button.setOnClickListener {
            var userNameText=userName.text.toString()
            var userPassWordText=userPass.text.toString()
            var num=contNum.text.toString()
            var userDetailing=Details(userNameText,userPassWordText,num)
            if(userNameText.isNotEmpty() && userPassWordText.isNotEmpty() && num.isNotEmpty()){
               database=FirebaseDatabase.getInstance().getReference("Details")
                database.child(userNameText).setValue(userDetailing).addOnSuccessListener {
                    // make custom dialog box of successfully add
                    dialog.show()


                }.addOnFailureListener {
                    Toast.makeText(this,"Firebase Error",Toast.LENGTH_SHORT).show()
                }
            }
        }
        btnGd.setOnClickListener {
            dialog.dismiss()
        }
        btnFeedbck.setOnClickListener {
            //intents
            Toast.makeText(this,"Feedback is given",Toast.LENGTH_SHORT).show()
        }
    }
}