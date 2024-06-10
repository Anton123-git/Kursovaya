package com.example.kursovaya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kursovaya.databinding.FragmentCourierOrdersBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Courier_orders.newInstance] factory method to
 * create an instance of this fragment.
 */
class Courier_orders : Fragment() {

    private lateinit var binding: FragmentCourierOrdersBinding
    private val adapter = CourierAllOrdersAdapter()

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
        binding = FragmentCourierOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initRecyclerView() {
        binding.rcViewAllOrders.layoutManager = LinearLayoutManager(context)
        binding.rcViewAllOrders.adapter = adapter

        // Инициализация данных
        val items = listOf(
            Ordres(1, 1, 1, "Резиновая баба", "С автоотсосом, и настоящей вагиной", "July 7, 2022"),
            Ordres(1, 1, 2, "Варенье", "С малиной", "July 7, 2022"),
            Ordres(1, 1, 3, "Фонарик", "Чисто вместо лампочки", "July 7, 2022"),
            Ordres(1, 1, 4, "Зипка Setner", "Залупа но пойдет", "July 7, 2022"),
            Ordres(1, 1, 5, "Комплект ФСО и СГУ", "Сута жи ес", "July 7, 2022"),

        )
        adapter.updateDataCAOrd(items)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment courier_order.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Courier_orders().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}