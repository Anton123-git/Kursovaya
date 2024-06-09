package com.example.kursovaya

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import java.util.*

class track : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var redDot: View
    private lateinit var blueDot1: View
    private lateinit var blueDot2: View
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
        blueDot1 = view.findViewById(R.id.blueDot1)
        blueDot2 = view.findViewById(R.id.blueDot2)
        imageView = view.findViewById(R.id.imageView)

        // Ждем пока макет будет отрисован
        imageView.doOnLayout {
            imageViewWidth = it.width
            imageViewHeight = it.height

            // Располагаем синие точки в случайных местах
            val blueDot1X = random.nextInt(imageViewWidth - blueDot1.width)
            val blueDot1Y = random.nextInt(imageViewHeight - blueDot1.height)
            blueDot1.translationX = blueDot1X.toFloat()
            blueDot1.translationY = blueDot1Y.toFloat()
            blueDot1.visibility = View.VISIBLE

            val blueDot2X = random.nextInt(imageViewWidth - blueDot2.width)
            val blueDot2Y = random.nextInt(imageViewHeight - blueDot2.height)
            blueDot2.translationX = blueDot2X.toFloat()
            blueDot2.translationY = blueDot2Y.toFloat()
            blueDot2.visibility = View.VISIBLE

            // Начинаем движение красной точки
            moveRedDot(blueDot1X.toFloat(), blueDot1Y.toFloat(), blueDot2X.toFloat(), blueDot2Y.toFloat())
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

    private fun moveRedDot(startX: Float, startY: Float, endX: Float, endY: Float) {
        val steps = 4
        val delay = 15000L

        val dx = (endX - startX) / steps
        val dy = (endY - startY) / steps

        for (i in 0..steps) {
            handler.postDelayed({
                redDot.translationX = startX + dx * i
                redDot.translationY = startY + dy * i
                redDot.visibility = View.VISIBLE
            }, delay * i)
        }
    }

    companion object {
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

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
