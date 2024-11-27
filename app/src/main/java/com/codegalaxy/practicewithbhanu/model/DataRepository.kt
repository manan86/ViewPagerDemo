package com.codegalaxy.practicewithbhanu.model

import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataRepository @Inject constructor(private val apiService: ApiService, private val dataDao: DataDao
    ) : IDataRepository {
    override suspend fun storeDataAPIUsingREPO(data: DataRequest): Response<DataResponse> {
        return apiService.saveDataToAPI(data)
    }

    override suspend fun insertDataLocally(dataEntity: DataEntity): Long {
        return dataDao.insert(dataEntity)
    }

    override suspend fun findDataByNameAndYear(name: String, year: String): DataEntity? {
        return dataDao.findDataByNameAndYear(name, year)
    }

    override suspend fun getAllData(): List<DataEntity> {
        
        return dataDao.getAllData()
    }
}
