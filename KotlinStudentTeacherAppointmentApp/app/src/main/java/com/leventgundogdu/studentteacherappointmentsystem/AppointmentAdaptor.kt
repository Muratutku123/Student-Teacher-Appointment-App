package com.leventgundogdu.studentteacherappointmentsystem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AppointmentAdaptor(private val appointments: MutableList<Appointment>) : RecyclerView.Adapter<AppointmentAdaptor.ViewHolder>() {

    var onItemClick : ((Appointment) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView : ImageView = itemView.findViewById(R.id.imageView)
        val textView1 : TextView = itemView.findViewById(R.id.txtstudentName)
        val textView2 : TextView = itemView.findViewById(R.id.txtappointmentDate)
        val textView3 : TextView = itemView.findViewById(R.id.txtappointmentTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.appointment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val appointment = appointments[position]
        holder.imageView.setImageResource(appointment.image)
        holder.textView1.text = appointment.studentName
        holder.textView2.text = appointment.date
        holder.textView3.text = appointment.time

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(appointment)
        }
    }

    override fun getItemCount(): Int {
        return appointments.size
    }

    fun addAppointment(appointment: Appointment) {
        appointments.add(appointment)
        notifyDataSetChanged()
    }

}