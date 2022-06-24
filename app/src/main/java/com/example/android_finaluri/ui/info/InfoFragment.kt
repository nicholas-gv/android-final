package com.example.android_finaluri.ui.info

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.android_finaluri.ViewPagerAdapter
import com.example.android_finaluri.databinding.FragmentInfoBinding
import com.example.android_finaluri.retrofit.RestClient
import com.example.android_finaluri.retrofit.dto.ApiData
import com.example.android_finaluri.retrofit.dto.FieldsProto
import com.example.android_finaluri.retrofit.dto.Info
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoFragment : Fragment() {

    private var _binding: FragmentInfoBinding? = null
    private var textsList: List<String?> = emptyList()

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
        RestClient.initClient()
        RestClient.reqResApi.getInfo("info").enqueue(object:Callback<List<ApiData<FieldsProto<Info>>>>{
            override fun onResponse(
                call: Call<List<ApiData<FieldsProto<Info>>>>,
                response: Response<List<ApiData<FieldsProto<Info>>>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.first()?.fieldsProto?.let {
                        textsList += (it.text1?.stringValue)
                        textsList += (it.text2?.stringValue)
                        textsList += (it.text3?.stringValue)
                    }
                    view_pager.adapter = ViewPagerAdapter(textsList)
                    view_pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
                }
            }

            override fun onFailure(
                call: Call<List<ApiData<FieldsProto<Info>>>>,
                t: Throwable
            ) {
                Log.d("error", t.message.toString())
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}