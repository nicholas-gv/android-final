package com.example.android_finaluri.ui.home

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android_finaluri.CountDownService
import com.example.android_finaluri.databinding.FragmentHomeBinding
import kotlin.math.roundToInt

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var timer: CountDownTimer
    private lateinit var timerButton: Button
    private lateinit var txtMinute: TextView
    private lateinit var txtSecond: TextView
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time: Long = 1500
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        txtMinute = binding.txtMinute
        txtSecond = binding.txtSecond
        timerButton = binding.timerButton

        timerButton.setOnClickListener { startStopTimer() }

        serviceIntent = Intent(requireContext(), CountDownService::class.java)
        requireActivity().registerReceiver(updateTime, IntentFilter(CountDownService.TIMER_UPDATED))

        return root
    }

    private fun resetTimer() {
        stopTimer()
        time = 1500
        txtMinute.text = getMinutesString(time)
        txtSecond.text = getSecondsString(time)
    }

    private fun startStopTimer() {
        if(timerStarted)
            stopTimer()
        else
            startTimer()
    }

    private fun startTimer() {
        serviceIntent.putExtra(CountDownService.TIME_EXTRA, time)
        requireActivity().startService(serviceIntent)
        timerButton.text = "Stop"
        timerStarted = true
    }

    private fun stopTimer() {
        requireActivity().stopService(serviceIntent)
        timerButton.text = "Start"
        timerStarted = false
    }

    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getLongExtra(CountDownService.TIME_EXTRA,1500)
            txtMinute.text = getMinutesString(time)
            txtSecond.text = getSecondsString(time)
        }
    }

    private fun getMinutesString(time: Long): String {
        val minutes = time/60
        return minutes.toString()
    }

    private fun getSecondsString(time: Long): String {
        val seconds = time%60
        return seconds.toString()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}