package com.example.android_finaluri.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.android_finaluri.databinding.FragmentSettingsBinding
import com.example.apps.android.davaleba8.data.DataRecord

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private lateinit var settingsViewModel: SettingsViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val settingsViewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)

        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val workTime: EditText = binding.datarecordRecordWork
        val restTime: EditText = binding.datarecordRecordRest

        settingsViewModel.getConfig("work_time").observe(viewLifecycleOwner) {
            workTime.setText(it?.value ?: "25")
        }

        settingsViewModel.getConfig("rest_time").observe(viewLifecycleOwner) {
            restTime.setText(it?.value ?: "5")
        }

        val btnSaveWork = binding.btnSaveWork
        val btnSaveRest = binding.btnSaveRest

        btnSaveWork.setOnClickListener(fun (view) {
            val work_time = binding.datarecordRecordWork.text.toString()
            val item = DataRecord(id = 1, name = "work_time", value = work_time)
            settingsViewModel.update(item)
        })

        btnSaveRest.setOnClickListener(fun (view) {
            val rest_time = binding.datarecordRecordRest.text.toString()
            val item = DataRecord(id = 2, name = "rest_time", value = rest_time)
            settingsViewModel.update(item)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}