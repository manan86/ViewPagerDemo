package com.codegalaxy.practicewithbhanu.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codegalaxy.practicewithbhanu.UIState
import com.codegalaxy.practicewithbhanu.di.NoInternetConnectionException
import com.codegalaxy.practicewithbhanu.model.DataEntity
import com.codegalaxy.practicewithbhanu.model.DataFromAPI
import com.codegalaxy.practicewithbhanu.model.DataRequest
import com.codegalaxy.practicewithbhanu.model.DataResponse
import com.codegalaxy.practicewithbhanu.model.IDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: IDataRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState<DataResponse>>()
    val uiState: LiveData<UIState<DataResponse>> = _uiState

    fun saveDataFromViewModel(name: String, yearModel: String, price: String, cpu: String, hardDisk: String) {
        viewModelScope.launch {
            _uiState.postValue(UIState.Loading)

            try {
                val existingData = repository.findDataByNameAndYear(name, yearModel)

                if (existingData != null) {
                    _uiState.postValue(UIState.Error("Data is already present in the database"))
                } else {
                    val dataObject = DataRequest(
                        name = name,
                        data = DataFromAPI(
                            year = yearModel,
                            price = price,
                            cpu = cpu,
                            hardDisk = hardDisk
                        )
                    )

                    val response = repository.storeDataAPIUsingREPO(dataObject)
                    if (response.isSuccessful) {
                        val newEntity = DataEntity(
                            name = name,
                            year = yearModel,
                            price = price,
                            cpu = cpu,
                            hardDisk = hardDisk
                        )
                        repository.insertDataLocally(newEntity)
                        _uiState.value = UIState.Success(response.body()!!)
                    } else {
                        _uiState.value = UIState.Error("Failed to save data to the API. Try again.")
                    }
                }
            } catch (e: NoInternetConnectionException) {
                _uiState.postValue(UIState.Error(e.message ?: "Unknown error occurred"))
            } catch (e: Exception) {
                _uiState.postValue(UIState.Error("Something went wrong. Please try again later."))
            }
        }
    }
}



/*

@HiltViewModel
class DataViewModel @Inject constructor(
    private val repository: IDataRepository
) : ViewModel() {

    private val _uiState = MutableLiveData<UIState<DataResponse>>()
    val uiState: LiveData<UIState<DataResponse>> = _uiState

    fun saveDataFromViewModel(name: String, yearModel: String, price: String, cpu: String, hardDisk: String) {
        viewModelScope.launch {
            _uiState.postValue(UIState.Loading)

            val existingData = repository.findDataByNameAndYear(name, yearModel)

            if (existingData != null) {
                _uiState.postValue(UIState.Error("Data is already present in the database"))
            } else {
                val dataObject = DataRequest(
                    name = name,
                    data = DataFromAPI(
                        year = yearModel,
                        price = price,
                        cpu = cpu,
                        hardDisk = hardDisk
                    )
                )

                val response = repository.storeDataAPIUsingREPO(dataObject)
                if (response.isSuccessful) {
                    val newEntity = DataEntity(
                        name = name,
                        year = yearModel,
                        price = price,
                        cpu = cpu,
                        hardDisk = hardDisk
                    )
                    repository.insertDataLocally(newEntity)
                    _uiState.value = (UIState.Success(response.body()!!))
                } else {
                    _uiState.value = (UIState.Error("Failed to save data to the API. Try again."))
                }
            }
        }
    }
}
*/