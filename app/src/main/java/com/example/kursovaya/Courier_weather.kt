package com.example.kursovaya

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.kursovaya.databinding.FragmentCourierWeatherBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import org.json.JSONObject

const val API_KEY = "8641ff60f6b049f58b2151547240207"

@Suppress("DEPRECATION")
class Courier_weather : Fragment() {

    private lateinit var fLocationClient: FusedLocationProviderClient

    private val fList = listOf(
        hours.newInstance(),
        days.newInstance()
    )

    private val tList = listOf(
        "HOURS",
        "DAYS"
    )

    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentCourierWeatherBinding
    private val model : WeatherViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCourierWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermission()
        init()
        updateCurrentCard()
        //getLacation()
        requestWeatherData("KOSTROMA")
    }

    private fun init() = with(binding){
        fLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        val adapter = VpAdapter(activity as FragmentActivity, fList)
        vp.adapter = adapter
        TabLayoutMediator(tabLayout, vp){
            tab, pos -> tab.text = tList[pos]
        }.attach()
        /*ibSync.setOnClickListener{
            getLacation()
        }*/
    }


    private fun getLacation(){
        val ct = CancellationTokenSource()
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        fLocationClient
            .getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, ct.token)// получение местоположения
            .addOnCompleteListener{
                requestWeatherData("${it.result.latitude},${it.result.longitude}")
            }
    }

    private fun updateCurrentCard() = with(binding){
        model.liveDataCurrent.observe(viewLifecycleOwner){
            tvCity.text = it.city
            tvDate.text = it.time
            Picasso.get().load("https:" + it.imageUrl).into(im)
            tvConditionMaxMinT.text = "${it.condition} +${it.minTemp}...+${it.maxTemp}"
            tvCurrentT.text = "+${it.currentTemp}°C"
        }
    }

    private fun permissionListener(){
        pLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()){
            Toast.makeText(activity, "Permission is $it", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkPermission(){
        if(!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)){
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun requestWeatherData(city: String){
        val url = "https://api.weatherapi.com/v1/forecast.json?key=" +
                "$API_KEY" +
                "&q=" +
                "$city" +
                "&days=" +
                "7" +
                "&aqi=no&alerts=no"

        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                result -> parseWeatherData(result)
            },
            {
                error -> Log.d("MyLog", "Error: $error")
            }
        )
        queue.add(request)
    }
    private fun parseWeatherData(result: String){
        val mainObject = JSONObject(result)
        val list = parseDays(mainObject)
        parseCurrentData(mainObject, list[0])
    }

    private fun parseDays(mainObject: JSONObject): List<WeatherModel>{
        val list = ArrayList<WeatherModel>()
        val daysArray = mainObject.getJSONObject("forecast").getJSONArray("forecastday")
        val name = mainObject.getJSONObject("location").getString("name")
        for (i in 0 until daysArray.length()){
            val day = daysArray[i] as JSONObject
            val item = WeatherModel(
                name,
                day.getString("date"),
                day.getJSONObject("day").getJSONObject("condition").getString("text"),
                "",
                day.getJSONObject("day").getString("maxtemp_c"),
                day.getJSONObject("day").getString("mintemp_c"),
                day.getJSONObject("day").getJSONObject("condition").getString("icon"),
                day.getJSONArray("hour").toString()
            )
            list.add(item)
        }
        return list
    }

    private fun parseCurrentData(mainObject: JSONObject, weatherItem: WeatherModel){
        val item = WeatherModel(
            /* name city */mainObject.getJSONObject("location").getString("name"),
            /* time */mainObject.getJSONObject("current").getString("last_updated"),
            /* condition */mainObject.getJSONObject("current").getJSONObject("condition").getString("text"),
            /* current temp */mainObject.getJSONObject("current").getString("temp_c"),
            /* max temp */weatherItem.maxTemp,
            /* min temp */weatherItem.minTemp,
            /* url icon */mainObject.getJSONObject("current").getJSONObject("condition").getString("icon"),
            /* прогноз по часам */weatherItem.hours
        )
        model.liveDataCurrent.value = item
        Log.d("MyLog", "City: ${item.city}")
        Log.d("MyLog", "Time: ${item.time}")
        Log.d("MyLog", "Condition: ${item.condition}")
        Log.d("MyLog", "CurrentTemp: ${item.currentTemp}")
        Log.d("MyLog", "ImageUrl: ${item.imageUrl}")
        Log.d("MyLog", "Min Temp: ${item.minTemp}")
        Log.d("MyLog", "Max Temp: ${item.maxTemp}")
        Log.d("MyLog", "Hours: ${item.hours}") //Вызов логов для проверки валидности данных которые мы пытаемся вызвать
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Courier_weather()
    }
}