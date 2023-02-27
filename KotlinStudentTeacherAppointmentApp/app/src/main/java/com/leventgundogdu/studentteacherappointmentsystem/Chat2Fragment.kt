package com.leventgundogdu.studentteacherappointmentsystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.leventgundogdu.studentteacherappointmentsystem.databinding.FragmentChatBinding


class Chat2Fragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter : ChatRecyclerAdapter
    private var chats = arrayListOf<Chat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore = Firebase.firestore
        auth = Firebase.auth
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ChatRecyclerAdapter()
        binding.chatRecycler.adapter = adapter
        binding.chatRecycler.layoutManager = LinearLayoutManager(requireContext())

        val userName = "ugur.kaplancali@yeditepe.edu.tr"

        binding.sendButton.setOnClickListener {
            //send button'a tiklandiginda yapilacaklar
            val chatText = binding.chatText.text.toString()
            auth.currentUser?.let {
                val user = it.email
                val chatText = binding.chatText.text.toString()
                val date = FieldValue.serverTimestamp()

                val dataMap = HashMap<String, Any>()
                dataMap.put("text",chatText)
                dataMap.put("user",user!!)
                dataMap.put("date",date)

                firestore.collection("$userName ile $user").add(dataMap).addOnSuccessListener {
                    binding.chatText.setText("")
                }.addOnFailureListener { exception ->
                    //hata alınınca execption olarak cevap veriyor
                    Toast.makeText(requireContext(),exception.localizedMessage, Toast.LENGTH_LONG).show()
                    binding.chatText.setText("")
                }
            }
        }

        val user = auth.currentUser?.email
        firestore.collection("$userName ile $user").orderBy("date",Query.Direction.ASCENDING).addSnapshotListener { value, error ->
            if (error != null) {
                Toast.makeText(requireContext(),error.localizedMessage, Toast.LENGTH_LONG).show()
            }
            else {
                if (value != null) {
                    if (value.isEmpty) {
                        Toast.makeText(requireContext(),"Mesaj Yok", Toast.LENGTH_LONG).show()
                    } else {
                        val documents = value.documents
                        chats.clear()
                        for (document in documents) {
                            val text = document.get("text") as String
                            val user = document.get("user") as String
                            val chat = Chat(user,text)
                            chats.add(chat)
                            adapter.chats = chats
                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}