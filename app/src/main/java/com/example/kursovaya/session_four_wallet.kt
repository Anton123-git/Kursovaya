package com.example.kursovaya

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kursovaya.databinding.FragmentSessionFourWalletBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.math.abs

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class session_four_wallet : Fragment() {

    private lateinit var binding: FragmentSessionFourWalletBinding
    private val adapter = HistoryAdapter()

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
        binding = FragmentSessionFourWalletBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Установите белый цвет для статус-бара
        activity?.window?.statusBarColor = resources.getColor(android.R.color.white, requireActivity().theme)

        // Установите темные символы для статус-бара
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.window?.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
        } else {
            @Suppress("DEPRECATION")
            activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // Инициализация RecyclerView
        initRecyclerView()

        // Обработчик нажатия на ImageView
        val imgCard: ImageView = view.findViewById(R.id.zalupa)
        imgCard.setOnClickListener {
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frameeror, session_four_payment_method())
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }

    private fun initRecyclerView() {
        binding.rcView.layoutManager = LinearLayoutManager(context)
        binding.rcView.adapter = adapter

        // Получаем платежи пользователя
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val paymentsRef = FirebaseDatabase.getInstance().getReference("payments").child(user.uid)
            paymentsRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val items = mutableListOf<ItemHistory>()
                    for (paymentSnapshot in dataSnapshot.children) {
                        val payment = paymentSnapshot.getValue(Payment::class.java)
                        if (payment != null) {
                            items.add(
                                ItemHistory(
                                    payment.index_pay,
                                    payment.amount > 0, // Assuming positive amount is a credit, negative is a debit
                                    abs(payment.amount),
                                    payment.title,
                                    payment.date
                                )
                            )
                        }
                    }
                    adapter.updateData(items)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Обработка ошибки
                }
            })
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            session_four_wallet().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
