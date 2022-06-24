package com.example.android_finaluri.ui.home

import android.app.NotificationChannel
import android.app.NotificationManager
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
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.android_finaluri.R
import com.example.android_finaluri.ui.settings.SettingsViewModel

class HomeFragment : Fragment() {

    private lateinit var settingsViewModel: SettingsViewModel

    private var _binding: FragmentHomeBinding? = null
    private lateinit var timer: CountDownTimer
    private lateinit var timerButton: Button
    private lateinit var txtMinute: TextView
    private lateinit var txtSecond: TextView
    private var timerStarted = false
    private lateinit var serviceIntent: Intent
    private var time: Long = 1500
    private val binding get() = _binding!!
    private val CHANNEL_ID = "pomodoroID"
    private val NOTIFICATION_ID = 42
    private val CHANNEL_NAME = "pomodoroChannel"

    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val settingsViewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        settingsViewModel.getConfig("work_time").observe(viewLifecycleOwner) {
            time = stringToMinute(it?.value ?: "25")
        }


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
            if (time<0 && isAdded()) {
                stopTimer()
            } else {
                txtMinute.text = getMinutesString(time)
                txtSecond.text = getSecondsString(time)
            }
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

    private fun stringToMinute(minute: String): Long {
        return minute.toLong()
    }

    private fun sendNotification(notificationContentTitle: String, notificationContentText: String){
        createNotificationChannel()

        val notification = NotificationCompat.Builder(requireContext(), CHANNEL_ID)
            .setContentTitle(notificationContentTitle)
            .setContentText(notificationContentText)
            .setSmallIcon(R.drawable.ic_baseline_star_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        val notificationManager = NotificationManagerCompat.from(requireContext())
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            val manager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}