package com.leventgundogdu.studentteacherappointmentsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.leventgundogdu.studentteacherappointmentsystem.databinding.ActivityAppointmentFormBinding

class AppointmentFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppointmentFormBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = Firebase.firestore
        auth = Firebase.auth

    }


    fun createAppointmentClicked(view: View) {

        val teacherEmail = binding.editTextTextEmail.text.toString()
        val time = binding.editTextTime.text.toString()
        val date = binding.editTextDate.text.toString()

        if (teacherEmail.equals("") || time.equals("") || date.equals("")) {
            Toast.makeText(this, "Teacher email, date or time cannot be empty!", Toast.LENGTH_LONG).show()
        } else {
            val currentUser = auth.currentUser
            if (currentUser != null) {
                val df = firestore.collection("Appointments").document(currentUser.uid)
                val postMap = hashMapOf<String, Any>()
                postMap.put("teacherEmail", teacherEmail)
                postMap.put("date", date)
                postMap.put("time", time)
                df.set(postMap).addOnSuccessListener {
                    Toast.makeText(this@AppointmentFormActivity, "Appointment Created!", Toast.LENGTH_LONG).show()
                    finish()
                }.addOnFailureListener {
                    Toast.makeText(this@AppointmentFormActivity, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

    }

}