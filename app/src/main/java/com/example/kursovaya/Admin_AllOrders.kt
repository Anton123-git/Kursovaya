    package com.example.kursovaya

    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import androidx.fragment.app.Fragment
    import androidx.recyclerview.widget.LinearLayoutManager
    import com.example.kursovaya.databinding.FragmentAdminAllOrdersBinding
    import com.google.firebase.auth.FirebaseAuth
    import com.google.firebase.database.DataSnapshot
    import com.google.firebase.database.DatabaseError
    import com.google.firebase.database.FirebaseDatabase
    import com.google.firebase.database.ValueEventListener
    import kotlin.math.abs

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

            val dbRef = FirebaseDatabase.getInstance().getReference("orders")
            dbRef.orderByValue().addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val items = mutableListOf<AdminAllOrderItem>()
                    for (child in dataSnapshot.children) {
                        val item = child.getValue(AdminAllOrderItem::class.java)
                            ?: AdminAllOrderItem(id = child.key) // используем key как id
                        items.add(item)
                    }
                    items.sortBy { it.id } // Sort the items by id_order
                    adapter.updateDataAAOrd(items)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error
                }
            })
        }



        override fun onInfoClick(item: AdminAllOrderItem) {
            val bundle = Bundle().apply {
                putString("id_order", item.id)
                putString("package_items", item.package_items)
                putString("date", item.date)
                // добавьте остальные данные
            }
            val fragment = admin_order()
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
