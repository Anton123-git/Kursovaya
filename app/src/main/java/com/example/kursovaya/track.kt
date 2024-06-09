package com.example.kursovaya

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.core.view.doOnLayout
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class track : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var redDot: View
    private lateinit var imageView: ImageView
    private val handler = Handler()
    private val random = Random()
    private var imageViewWidth = 0
    private var imageViewHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_track, container, false)

        // Получаем элементы
        val checkbox1 = view.findViewById<CheckBox>(R.id.checkbox1)
        val checkbox2 = view.findViewById<CheckBox>(R.id.checkbox2)
        val checkbox3 = view.findViewById<CheckBox>(R.id.checkbox3)
        val checkbox4 = view.findViewById<CheckBox>(R.id.checkbox4)

        redDot = view.findViewById(R.id.redDot)
        imageView = view.findViewById(R.id.imageView)

        // Ждем пока макет будет отрисован
        imageView.doOnLayout {
            imageViewWidth = it.width
            imageViewHeight = it.height
            moveRedDot()
        }

        // Создаем задачи для включения чекбоксов
        handler.postDelayed({
            checkbox1.isChecked = true
        }, 15000) // 15 секунд

        handler.postDelayed({
            checkbox2.isChecked = true
        }, 30000) // 30 секунд

        handler.postDelayed({
            checkbox3.isChecked = true
        }, 45000) // 45 секунд

        handler.postDelayed({
            checkbox4.isChecked = true
        }, 60000) // 60 секунд

        return view
    }

    private fun moveRedDot() {
        handler.postDelayed({
            val x = random.nextInt(imageViewWidth - redDot.width)
            val y = random.nextInt(imageViewHeight - redDot.height)
            redDot.translationX = x.toFloat()
            redDot.translationY = y.toFloat()
            redDot.visibility = View.VISIBLE
            moveRedDot()
        }, 1000)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            track().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
