package com.example.kursovaya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.RadioButton
import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [session_four_payment_method.newInstance] factory method to
 * create an instance of this fragment.
 */
class session_four_payment_method : Fragment() {
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
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_session_four_payment_method, container, false)

        val radioButton: RadioButton = view.findViewById(R.id.radBtn2)
        val listView: ListView = view.findViewById(R.id.cardList)

        val items = arrayListOf("**** **** **** 1234", "**** **** **** 4321", "**** **** **** 5678")

        val adapter = CardListAdapter(requireContext(), R.layout.card_items, items)
        listView.adapter = adapter

        radioButton.setOnCheckedChangeListener { _, isChecked ->
            listView.visibility = if (isChecked) View.VISIBLE else View.GONE
        }

        val addCard = view.findViewById<Button>(R.id.addCArd)
        addCard.setOnClickListener{
            // Создаем фрагмент, который будет отображаться
            val newFragment = addAcard()

            // Получаем менеджер фрагментов вложенного фрагмента
            val fragmentManager = childFragmentManager

            // Начинаем транзакцию
            val transaction = fragmentManager.beginTransaction()

            // Заменяем содержимое вложенного фрагмента на новый
            transaction.replace(R.id.addACardFrame, newFragment)

            // Коммитим транзакцию
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
         * @return A new instance of fragment session_four_payment_method.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            session_four_payment_method().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}