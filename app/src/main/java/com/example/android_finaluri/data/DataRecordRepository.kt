/****************************************************************
 *   Copyright (c) 2020 Salvatore Ventura <salvoventura@gmail.com>
 *
 *     File: DataRecordRepository.kt
 *
 *   Author: Salvatore Ventura <salvoventura@gmail.com>
 *     Date: 2/19/2020
 *  Purpose: Repository for the Record data class
 *           
 * Revision: 1
 *  Comment: Due to its simplicity, most of the code just exposes
 *           the functions from the DAO.
 *           However, notice how `allItems` is a local property
 *           populated via `DataRecordDao.getall()`
 *
 ****************************************************************/
 package com.example.apps.android.davaleba8.data

import androidx.lifecycle.LiveData

class DataRecordRepository(private val datarecordDao: DataRecordDao) {
    fun getConfig(name: String):LiveData<DataRecord> {
        return datarecordDao.getConfig(name)
    }

    suspend fun update(item: DataRecord) {
        datarecordDao.update(item)
    }


}