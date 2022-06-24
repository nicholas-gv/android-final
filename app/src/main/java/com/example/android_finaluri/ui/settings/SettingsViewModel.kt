package com.example.android_finaluri.ui.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.apps.android.davaleba8.data.AppRoomDatabase
import com.example.apps.android.davaleba8.data.DataRecord
import com.example.apps.android.davaleba8.data.DataRecordRepository
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: DataRecordRepository

    init {
        val dao = AppRoomDatabase.getDatabase(application).datarecordDao()
        repository = DataRecordRepository(dao)
    }

    fun update(item: DataRecord) = viewModelScope.launch {
        repository.update(item)
    }

    fun getConfig(name: String) = repository.getConfig(name)
}