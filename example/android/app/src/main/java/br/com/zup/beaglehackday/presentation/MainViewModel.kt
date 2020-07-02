/*
 * Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.zup.beaglehackday.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.zup.beaglehackday.data.repository.CharlesRepository
import kotlinx.coroutines.launch
import java.lang.Exception

sealed class Response {
    data class Loading(val loading: Boolean) : Response()
    data class Success(val circleId: String) : Response()
    data class Error(val exception: String) : Response()
}

class MainViewModel(
    private val repository: CharlesRepository = CharlesRepository()
) : ViewModel() {

    private val _response = MutableLiveData<Response>()

    val response: LiveData<Response>
        get() = _response

    fun getCircle(username: String) {
        viewModelScope.launch {
            try {
                _response.value = Response.Loading(true)
                val circleId = repository.getCircle(username)
                _response.value = Response.Success(circleId)
            } catch (e: Exception) {
                _response.value = Response.Error(e.toString())
            } finally {
                _response.value = Response.Loading(false)
            }
        }
    }
}
