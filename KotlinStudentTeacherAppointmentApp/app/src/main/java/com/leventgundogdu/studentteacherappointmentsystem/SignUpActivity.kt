package com.leventgundogdu.studentteacherappointmentsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.leventgundogdu.studentteacherappointmentsystem.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        firestore = Firebase.firestore

        //hiding the action bar
        val actionBar: ActionBar? = supportActionBar
        if (actionBar != null) {
            actionBar.hide()
        }

        //checkBox Logics

        val studentCheckBox = binding.checkBoxStudent
        val teacherCheckBox = binding.checkBoxTeacher

        studentCheckBox.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked) {
                teacherCheckBox.isChecked = false
            }
        }

        teacherCheckBox.setOnCheckedChangeListener { compoundButton, b ->
            if (compoundButton.isChecked) {
                studentCheckBox.isChecked = false
            }
        }

    }

    fun nextButtonClicked(view: View) {

        val fullName = binding.editTextRegisterName.text.toString()
        val email = binding.editTextRegisterEmail.text.toString()
        val password = binding.editTextRegisterPassword.text.toString()
        val studentCheckBox = binding.checkBoxStudent
        val teacherCheckBox = binding.checkBoxTeacher

        //checkbox validation
        if (!(teacherCheckBox.isChecked || studentCheckBox.isChecked)) {
            Toast.makeText(this, "Account type cannot be empty!", Toast.LENGTH_LONG).show()
            return
        }


        if (fullName.equals("") || email.equals("") || password.equals("")) {
            Toast.makeText(this, "full name, e-mail or password cannot be empty!", Toast.LENGTH_LONG).show()
        } else {

            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {

                Toast.makeText(this@SignUpActivity, "Account Created!", Toast.LENGTH_LONG).show()

                val currentUser = auth.currentUser
                val df = firestore.collection("Users").document(currentUser!!.uid)

                val postMap = hashMapOf<String, Any>()
                postMap.put("fullName", fullName)
                postMap.put("userEmail", email)
                postMap.put("password", password)

                //checkBox
                if (teacherCheckBox.isChecked) {
                    postMap.put("isTeacher", "1")
                }
                if (studentCheckBox.isChecked) {
                    postMap.put("isStudent", "1")
                }

                df.set(postMap).addOnSuccessListener {
                    checkUserAccessLevel(currentUser.uid)
                }.addOnFailureListener {
                    Toast.makeText(this@SignUpActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
                }

            }.addOnFailureListener {
                Toast.makeText(this@SignUpActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }

    }

    private fun checkUserAccessLevel(uid: String) {
        val uniqueId = firestore.collection("Users").document(uid)
        //extract the data from document

        uniqueId.get().addOnSuccessListener {
            Log.d("TAG", "onSuccess: ${it.data}")

            //identify if the user has access level
            if (it.getString("isTeacher") != null) {
                //user is admin
                val intent = Intent(this, TeacherFeedActivity::class.java)
                startActivity(intent)
                finish()
            }

            if (it.getString("isStudent") != null) {
                //user is not admin
                val intent = Intent(this, StudentFeedActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }



}