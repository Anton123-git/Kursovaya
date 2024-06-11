package com.example.kursovaya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kursovaya.databinding.FragmentAdminAllOrdersBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Admin_AllOrders.newInstance] factory method to
 * create an instance of this fragment.
 */
class Admin_AllOrders : Fragment(), OnItemClickListener {

    private lateinit var binding: FragmentAdminAllOrdersBinding
    private lateinit var adapter: AdminAllOerdersAdapter

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
        binding = FragmentAdminAllOrdersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewAO()
    }

    private fun initRecyclerViewAO() {
        adapter = AdminAllOerdersAdapter(this)
        binding.rcViewAdmAllOrders.layoutManager = LinearLayoutManager(context)
        binding.rcViewAdmAllOrders.adapter = adapter

        val items = listOf(
            AdminAllOrderItem("1", "1", "ул. Пушкина, д. 1", "Кострома", "8800552476", "там гречка еблан", "улица пушкина дом колотушкина", "Кострома", "+79109203434", "я долбаеб", "гречка","2kg","1000","2024-04-01", 1 , "VItalya CallOfDuty"),
            AdminAllOrderItem("2", "43", "ул. Пушкина, д. 2", "Кострома", "8874578", "там стекло еблан", "улица пушкина дом 2", "Кострома", "+79109203454", "я долбаеб", "фары","2kg","1000","2024-04-01", 1 , "VItalya CallOfDuty"),
            AdminAllOrderItem("3", "56", "ул. Пушкина, д. 3", "Кострома", "68845784", "там метал еблан", "улица пушкина дом 3", "Кострома", "+7934556434", "я не долбаеб", "гаечные ключи","2kg","1000","2024-04-01", 1 , "VItalya CallOfDuty"),
            AdminAllOrderItem("4", "2", "ул. Пушкина, д. 4", "Кострома", "880034578", "там клава еблан", "улица пушкина дом 4", "Кострома", "+79105666634", "я лох", "клавиатура","2kg","1000","2024-04-01", 1 , "VItalya CallOfDuty"),
            AdminAllOrderItem("5", "87", "ул. Пушкина, д. 5", "Кострома", "82345535", "там монитор еблан", "улица пушкина дом 5", "Кострома", "+79144556634", "я", "монитор","2kg","1000","2024-04-01", 1 , "VItalya CallOfDuty"),
        )
        adapter.updateDataAAOrd(items)
    }

    override fun onInfoClick(item: AdminAllOrderItem) {
        val bundle = Bundle().apply {
            putString("id_order", item.id_order)
            putString("package_items", item.package_items)
            putString("date", item.date)
            //Тут те данные которые парсятся из списка на экран информаци заказа


            // добавьте остальные данные
        }
        val fragment = admin_order() // на какой фрагмент уходят данные
        fragment.arguments = bundle

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Admin_AllOrders().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
