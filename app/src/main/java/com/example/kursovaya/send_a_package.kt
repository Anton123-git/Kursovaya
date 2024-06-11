package com.example.kursovaya

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [send_a_package.newInstance] factory method to
 * create an instance of this fragment.
 */
class send_a_package : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_send_a_package, container, false)

        val a_address = view.findViewById<EditText>(R.id.a_address)
        val a_state = view.findViewById<EditText>(R.id.a_state)
        val a_phone = view.findViewById<EditText>(R.id.a_phone)
        val a_others = view.findViewById<EditText>(R.id.a_others)
        val b_adsress = view.findViewById<EditText>(R.id.b_address)
        val b_state = view.findViewById<EditText>(R.id.b_state)
        val b_phone = view.findViewById<EditText>(R.id.b_phone)
        val b_others = view.findViewById<EditText>(R.id.b_others)
        val package_items = view.findViewById<EditText>(R.id.package_items)
        val weight = view.findViewById<EditText>(R.id.weight)
        val worth = view.findViewById<EditText>(R.id.worth)

        val btn = view.findViewById<CardView>(R.id.btn)
        btn.setOnClickListener{
            // Проверка на ввод
            if (a_address.text.toString().isEmpty() || a_state.text.toString().isEmpty() || a_phone.text.toString().isEmpty()||  b_adsress.text.toString().isEmpty()
                ||  b_state.text.toString().isEmpty() ||  b_phone.text.toString().isEmpty() ||  package_items.text.toString().isEmpty() || weight.text.toString().isEmpty()
            ||worth.text.toString().isEmpty()){
                Toast.makeText(context, "Поля не могут быть пустыми", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Получаем текст из полей
            val a_address_text = a_address.text.toString()
            val a_state_text = a_state.text.toString()
            val a_phone_text = a_phone.text.toString()
            val a_others_text = a_others.text.toString()
            val b_adsress_text = b_adsress.text.toString()
            val b_state_text = b_state.text.toString()
            val b_phone_text = b_phone.text.toString()
            val b_others_text = b_others.text.toString()
            val package_items_text = package_items.text.toString()
            val weight_text = weight.text.toString()
            val worth_text = worth.text.toString()



            // Получаем текущего пользователя
            val currentUser = FirebaseAuth.getInstance().currentUser
            val userId = currentUser?.uid ?: "" 
            val currentDate = Date()
            val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            val formattedDate = dateFormat.format(currentDate)

            // Запись данных в Firebase Realtime Database
            val db = FirebaseDatabase.getInstance().getReference("orders/$userId")
            val orderid = db.push().key ?: ""
            val order = AdminAllOrderItem(
                id_order = orderid,
                id_user = userId,
                a_address = a_address_text,
                a_state = a_state_text,
                a_phone = a_phone_text,
                a_others = a_others_text,
                b_address = b_adsress_text,
                b_state = b_state_text,
                b_phone = b_phone_text,
                b_others = b_others_text,
                package_items = package_items_text,
                weight = weight_text,
                worth = worth_text,
                date = formattedDate,
                residence_status = 1,
                c_name = ""

            )
            db.child(orderid).setValue(order)

            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame, send_a_package_recepit())
            transaction.addToBackStack(null)
            transaction.commit()
        }


        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment send_a_package.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            send_a_package().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}