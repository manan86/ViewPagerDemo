package com.codegalaxy.practicewithbhanu.model

import retrofit2.Response

interface IDataRepository {
    suspend fun storeDataAPIUsingREPO(data : DataRequest) : Response<DataResponse>
    suspend fun insertDataLocally(dataEntity: DataEntity): Long
    suspend fun findDataByNameAndYear(name: String, year: String): DataEntity?
    suspend fun getAllData(): List<DataEntity>
}


//add two para return int

//interface Result{
//    fun add(a : Int, b: Int) : Int
//}
//
//class Sum : Result{
//    override fun add(a: Int, b: Int): Int {
//        return a + b
//    }
//}
//
//class Addition : Result{
//    override fun add(a: Int, b: Int): Int {
//        return a + b
//    }
//}
//
//fun main(){
//    val sum : Result = Addition()
//    sum.add(5,3)
//}