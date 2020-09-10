/*
 * Copyright (c) 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA.
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
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
