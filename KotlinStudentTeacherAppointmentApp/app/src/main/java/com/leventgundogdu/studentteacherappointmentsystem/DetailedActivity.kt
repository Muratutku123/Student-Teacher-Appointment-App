package com.leventgundogdu.studentteacherappointmentsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailedActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val course = intent.getParcelableExtra<Course>("course")
        if (course != null) {
            val textView : TextView = findViewById(R.id.detailedActivityTv)
            val imageView: ImageView = findViewById(R.id.detailedActivityIv)

            textView.text = course.name
            imageView.setImageResource(course.image)

            val button = findViewById<Button>(R.id.buttonView)
            button.setOnClickListener {
                imageView.setImageDrawable(null)
                button.visibility = View.INVISIBLE
                if (textView.text == "Doç. Dr. Aşkın Demirağ") {
                    textView.setText(null)
                    val chatFragment = ChatFragment()
                    if(savedInstanceState == null) { // initial transaction should be wrapped like this
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.root_container, chatFragment)
                            .commitAllowingStateLoss()
                    }
                }
                else if (textView.text == "Doç. Dr. Uğur T. Kaplancalı") {
                    textView.setText(null)
                    val chatFragment = Chat2Fragment()
                    if(savedInstanceState == null) { // initial transaction should be wrapped like this
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.root_container_2, chatFragment)
                            .commitAllowingStateLoss()
                    }
                }
                else if (textView.text == "Dr. Öğr. Üyesi Manu Dube") {
                    textView.setText(null)
                    val chatFragment = Chat3Fragment()
                    if(savedInstanceState == null) { // initial transaction should be wrapped like this
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.root_container_3, chatFragment)
                            .commitAllowingStateLoss()
                    }
                }
                if (textView.text == "Dr. Öğr. Üyesi Tufan Ekin") {
                    textView.setText(null)
                    val chatFragment = Chat4Fragment()
                    if(savedInstanceState == null) { // initial transaction should be wrapped like this
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.root_container_4, chatFragment)
                            .commitAllowingStateLoss()
                    }
                }
                if (textView.text == "Dr. Öğr. Üyesi Engin Kandıran") {
                    textView.setText(null)
                    val chatFragment = Chat5Fragment()
                    if(savedInstanceState == null) { // initial transaction should be wrapped like this
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.root_container_5, chatFragment)
                            .commitAllowingStateLoss()
                    }
                }
                if (textView.text == "Dr. Öğr. Üyesi Çağla Özen") {
                    textView.setText(null)
                    val chatFragment = Chat6Fragment()
                    if(savedInstanceState == null) { // initial transaction should be wrapped like this
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.root_container_6, chatFragment)
                            .commitAllowingStateLoss()
                    }
                }

            }
        }
    }


}