package com.example.android_finaluri.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android_finaluri.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var timer: CountDownTimer
    private lateinit var timerStartButton: Button
    private lateinit var txtMinute: TextView
    private lateinit var txtSecond: TextView

    // This property is only valid between onCreateView and
    // onDestroyView.
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
        timerStartButton = binding.startTimerButton

        timerStartButton.setOnClickListener {
            timer = object: CountDownTimer(1500000, 1000) {
                override fun onTick(remaigningMs: Long) {
                    txtMinute.text = (remaigningMs/(60*1000)).toString()
                    txtSecond.text = ((remaigningMs/1000)%60).toString()
                }

                override fun onFinish() {
                    txtMinute.text = "0"
                    txtSecond.text = "0"
                    timer.cancel()
                }
            }
            timer.start()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}