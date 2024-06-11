package com.example.kursovaya

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kursovaya.databinding.FragmentAdminUsersBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [admin_users.newInstance] factory method to
 * create an instance of this fragment.
 */
class admin_users : Fragment() {

    private lateinit var binding: FragmentAdminUsersBinding
    private val adapter = UsersAdapter()

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
        binding = FragmentAdminUsersBinding.inflate(inflater, container, false)
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
        initRecyclerViewA()

    }

    private fun initRecyclerViewA() {
        binding.rcViewUsersD.layoutManager = LinearLayoutManager(context)
        binding.rcViewUsersD.adapter = adapter

        // Инициализация данных
        val items = listOf(
            UserItem("Anton Gandon", "zaebis", 1, 1),
            UserItem("Vlad Ebanat", "zaebis", 2, 3),
            UserItem("Ovcut Rahal", "zaebis", 3, 2),
            UserItem("Mamut Rahal", "zaebis", 4, 1),
            UserItem("Jechka Dirova", "zaebis", 5, 2),
            UserItem("Vitalya CallOfDuty", "zaebis", 6, 1),
            UserItem("Duty Free", "zaebis", 7, 2),
            UserItem("Super Man", "zaebis", 8, 2),
            )
        adapter.updateDataUser(items)
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment admin_users.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            admin_users().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}