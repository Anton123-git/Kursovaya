package com.example.kursovaya

import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.text.InputFilter
import android.text.InputType
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [addAcard.newInstance] factory method to
 * create an instance of this fragment.
 */
class addAcard : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_add_acard, container, false)

        val cardNumber = view.findViewById<EditText>(R.id.editTextText3)
        val cardDate = view.findViewById<EditText>(R.id.editTextText13)
        val cardCVC = view.findViewById<EditText>(R.id.editTextText12)

        cardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 16) {
                    cardNumber.clearFocus()
                    cardDate.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        cardDate.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s?.length == 2 && before < count) {
                    cardDate.setText("$s/")
                    cardDate.setSelection(cardDate.text.length)
                }
                if (s?.length == 5) {
                    cardDate.clearFocus()
                    cardCVC.requestFocus()
                }
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })

        cardCVC.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s?.length == 3) {
                    view?.clearFocus()
                    // Скрытие клавиатуры
                    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(view?.windowToken, 0)
                }
            }
        })

        cardNumber.inputType = InputType.TYPE_CLASS_NUMBER
        cardDate.inputType = InputType.TYPE_CLASS_NUMBER
        cardCVC.inputType = InputType.TYPE_CLASS_NUMBER

        val add = view.findViewById<Button>(R.id.add)
        add.setOnClickListener {
            // Получаем текст из полей
            val cardNumberText = cardNumber.text.toString()
            val cardDateText = cardDate.text.toString()
            val cardCVCText = cardCVC.text.toString()

            // Очищаем поля
            cardNumber.text.clear()
            cardDate.text.clear()
            cardCVC.text.clear()

            // Получаем текущего пользователя
            val currentUser = FirebaseAuth.getInstance().currentUser
            val userId = currentUser?.uid ?: ""

            // Запись данных в Firebase Realtime Database
            val db = FirebaseDatabase.getInstance().getReference("cards/$userId")
            val cardId = db.push().key ?: ""
            val card = cards(
                id = cardId,
                number = cardNumberText ?: "",
                date = cardDateText ?: "",
                cvc = cardCVCText ?: ""
            )
            db.child(cardId).setValue(card)
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
         * @return A new instance of fragment addAcard.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            addAcard().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}