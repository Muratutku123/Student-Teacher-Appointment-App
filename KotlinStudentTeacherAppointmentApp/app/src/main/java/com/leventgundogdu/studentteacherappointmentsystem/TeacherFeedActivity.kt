package com.leventgundogdu.studentteacherappointmentsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.leventgundogdu.studentteacherappointmentsystem.databinding.ActivityTeacherFeedBinding

class TeacherFeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeacherFeedBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private lateinit var recyclerView: RecyclerView
    //private lateinit var appointmentList : ArrayList<Appointment>
    private lateinit var adapter: AppointmentAdaptor
    private lateinit var appointmentAdaptor: AppointmentAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        firestore = Firebase.firestore

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = AppointmentAdaptor(mutableListOf())
        recyclerView.adapter = adapter

        getData() { studentName, date, time ->

            val appointment = Appointment(R.drawable.user1, studentName, date, time)
            adapter.addAppointment(appointment)
            adapter.notifyDataSetChanged()

        }

     }

    private fun getData(callback: (studentName: String, date: String, time: String) -> Unit) {

        val appointmentData1 = firestore.collection("Appointments").document("n8tYYF0nYRahtSxfQjc6YSsYXOJ2").collection("appointments")
        val userData = firestore.collection("Users")

        appointmentData1.get().addOnSuccessListener {
            for (document in it) {
                if (document.getString("teacherEmail").equals(auth.currentUser!!.email)) {
                    val date = document.getString("date")!!
                    val time = document.getString("time")!!

                    userData.get().addOnSuccessListener {
                        for (studentData in it) {
                            if ("n8tYYF0nYRahtSxfQjc6YSsYXOJ2" == studentData.id) {
                                val studentName = studentData.getString("fullName")!!
                                println(studentName)
                                callback(studentName, date, time)
                            }
                        }
                    }
                }
            }
        }

        val appointmentData2 = firestore.collection("Appointments").document("lJkJzWHIEwWbcOX0vHMBkg8PaBz2").collection("appointments")

        appointmentData2.get().addOnSuccessListener {
            for (document in it) {
                if (document.getString("teacherEmail").equals(auth.currentUser!!.email)) {
                    val date = document.getString("date")!!
                    val time = document.getString("time")!!

                    userData.get().addOnSuccessListener {
                        for (studentData in it) {
                            if ("lJkJzWHIEwWbcOX0vHMBkg8PaBz2" == studentData.id) {
                                val studentName = studentData.getString("fullName")!!
                                println(studentName)
                                callback(studentName, date, time)
                            }
                        }
                    }
                }
            }
        }

        val appointmentData3 = firestore.collection("Appointments").document("HYYtzSqOIOgLylriqoAbwZPWrHP2").collection("appointments")

        appointmentData3.get().addOnSuccessListener {
            for (document in it) {
                if (document.getString("teacherEmail").equals(auth.currentUser!!.email)) {
                    val date = document.getString("date")!!
                    val time = document.getString("time")!!

                    userData.get().addOnSuccessListener {
                        for (studentData in it) {
                            if ("HYYtzSqOIOgLylriqoAbwZPWrHP2" == studentData.id) {
                                val studentName = studentData.getString("fullName")!!
                                println(studentName)
                                callback(studentName, date, time)
                            }
                        }
                    }
                }
            }
        }

        val appointmentData4 = firestore.collection("Appointments").document("23QKAwy9FzP4WWtJkW8eN0agc6y1").collection("appointments")

        appointmentData4.get().addOnSuccessListener {
            for (document in it) {
                if (document.getString("teacherEmail").equals(auth.currentUser!!.email)) {
                    val date = document.getString("date")!!
                    val time = document.getString("time")!!

                    userData.get().addOnSuccessListener {
                        for (studentData in it) {
                            if ("23QKAwy9FzP4WWtJkW8eN0agc6y1" == studentData.id) {
                                val studentName = studentData.getString("fullName")!!
                                println(studentName)
                                callback(studentName, date, time)
                            }
                        }
                    }
                }
            }
        }

        val appointmentData5 = firestore.collection("Appointments").document("xaKvRIbN9YXk6o6rupfOhyLc58W2").collection("appointments")

        appointmentData5.get().addOnSuccessListener {
            for (document in it) {
                if (document.getString("teacherEmail").equals(auth.currentUser!!.email)) {
                    val date = document.getString("date")!!
                    val time = document.getString("time")!!

                    userData.get().addOnSuccessListener {
                        for (studentData in it) {
                            if ("xaKvRIbN9YXk6o6rupfOhyLc58W2" == studentData.id) {
                                val studentName = studentData.getString("fullName")!!
                                println(studentName)
                                callback(studentName, date, time)
                            }
                        }
                    }
                }
            }
        }

        val appointmentData6 = firestore.collection("Appointments").document("xDzaBGjrnEWxHdqq2cgcQfl4DnU2").collection("appointments")

        appointmentData6.get().addOnSuccessListener {
            for (document in it) {
                if (document.getString("teacherEmail").equals(auth.currentUser!!.email)) {
                    val date = document.getString("date")!!
                    val time = document.getString("time")!!

                    userData.get().addOnSuccessListener {
                        for (studentData in it) {
                            if ("xDzaBGjrnEWxHdqq2cgcQfl4DnU2" == studentData.id) {
                                val studentName = studentData.getString("fullName")!!
                                println(studentName)
                                callback(studentName, date, time)
                            }
                        }
                    }
                }
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.teacher_feed_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.sign_out) {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        if (item.itemId == R.id.chat) {
            val user2Fragment = User2Fragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_user_2, user2Fragment)
                .commitAllowingStateLoss()

        }

        return super.onOptionsItemSelected(item)
    }

}