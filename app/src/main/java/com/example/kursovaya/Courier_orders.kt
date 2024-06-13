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
class Courier_orders : Fragment(), OnItemClickListenerC {

    private lateinit var binding: FragmentCourierOrdersBinding
    private lateinit var adapter: CourierAllOrdersAdapter

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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewC()
    }

    private fun initRecyclerViewC() {
        adapter = CourierAllOrdersAdapter(this)
        binding.rcViewAllOrders.layoutManager = LinearLayoutManager(context)
        binding.rcViewAllOrders.adapter = adapter

        // Инициализация данных
        val items = listOf(
            Ordres("1", "1", "ул. Пушкина, д. 1", "Кострома", "8800552476", "там гречка еблан", "улица пушкина дом колотушкина", "Кострома", "+79109203434", "я долбаеб", "гречка","2kg","1000","2024-04-01", 1 , "VItalya CallOfDuty"),
            Ordres("2", "43", "ул. Пушкина, д. 2", "Кострома", "8874578", "там стекло еблан", "улица пушкина дом 2", "Кострома", "+79109203454", "я долбаеб", "фары","2kg","1000","2024-04-01", 1 , "VItalya CallOfDuty"),
            Ordres("3", "56", "ул. Пушкина, д. 3", "Кострома", "68845784", "там метал еблан", "улица пушкина дом 3", "Кострома", "+7934556434", "я не долбаеб", "гаечные ключи","2kg","1000","2024-04-01", 1 , "VItalya CallOfDuty"),
            Ordres("4", "2", "ул. Пушкина, д. 4", "Кострома", "880034578", "там клава еблан", "улица пушкина дом 4", "Кострома", "+79105666634", "я лох", "клавиатура","2kg","1000","2024-04-01", 1 , "VItalya CallOfDuty"),
            Ordres("5", "87", "ул. Пушкина, д. 5", "Кострома", "82345535", "там монитор еблан", "улица пушкина дом 5", "Кострома", "+79144556634", "я", "монитор","2kg","1000","2024-04-01", 1 , "VItalya CallOfDuty"),

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

    override fun onInfoClickС(item: Ordres) {
        val bundle = Bundle().apply {
            putString("id_order", item.id_order)
            putString("package_items", item.package_items)
            putString("date", item.date)
            //Тут те данные которые парсятся из списка на экран информаци заказа


            // добавьте остальные данные
        }
        val fragment = Courier_orderinfo() // на какой фрагмент уходят данные
        fragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .addToBackStack(null)
            .commit()
    }


}