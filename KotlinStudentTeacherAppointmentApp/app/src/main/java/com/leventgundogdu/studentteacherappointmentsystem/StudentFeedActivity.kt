package com.leventgundogdu.studentteacherappointmentsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.leventgundogdu.studentteacherappointmentsystem.databinding.ActivityStudentFeedBinding

class StudentFeedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStudentFeedBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    private lateinit var courseList: ArrayList<Course>
    private lateinit var courseAdaptor: CourseAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentFeedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        recyclerView = binding.recyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        courseList = ArrayList()


        courseList.add(Course(R.drawable.course1, "Doç. Dr. Aşkın Demirağ",))
        courseList.add(Course(R.drawable.course2, "Doç. Dr. Uğur T. Kaplancalı" ))
        courseList.add(Course(R.drawable.course3, "Dr. Öğr. Üyesi Manu Dube" ))
        courseList.add(Course(R.drawable.course4, "Dr. Öğr. Üyesi Tufan Ekin" ))
        courseList.add(Course(R.drawable.course5, "Dr. Öğr. Üyesi Engin Kandıran" ))
        courseList.add(Course(R.drawable.course6, "Dr. Öğr. Üyesi Çağla Özen" ))

        courseAdaptor = CourseAdaptor(courseList)
        recyclerView.adapter = courseAdaptor

        courseAdaptor.onItemClick = {
            val intent = Intent(this, DetailedActivity::class.java)
            intent.putExtra("course", it)
            startActivity(intent)

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.feed_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.sign_out) {
            auth.signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        if (item.itemId == R.id.add_appointment) {
            val intent = Intent(this, AppointmentFormActivity::class.java)
            startActivity(intent)

        }

        return super.onOptionsItemSelected(item)
    }

}