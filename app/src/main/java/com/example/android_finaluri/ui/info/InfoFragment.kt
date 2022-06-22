package com.example.android_finaluri.ui.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.android_finaluri.ViewPagerAdapter
import com.example.android_finaluri.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private val texts: List<String> = listOf(
        "The Pomodoro Technique is a time management method developed by Francesco Cirillo in the" +
                " late 1980s It uses a kitchen timer to break work into intervals, typically" +
                " 25 minutes in length, separated by short breaks.",
        "A goal of the technique is to reduce the effect of internal and external interruptions " +
                "on focus and flow. The technique has been widely popularized by apps and " +
                "websites providing timers and instructions.",
        "It should be mentioned that specific cases can be handled with common sense: If you " +
                "finish a task while the Pomodoro is still ticking, the following rule applies: " +
                "If a Pomodoro begins, it has to ring. It’s a good idea to take advantage of the " +
                "opportunity for over-learning, using the remaining portion of the Pomodoro to " +
                "review or repeat what you’ve done, make small improvements, and note what you’ve" +
                " learned until the Pomodoro rings.")

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val view_pager = binding.viewPager
        view_pager.adapter = ViewPagerAdapter(texts)
        view_pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}