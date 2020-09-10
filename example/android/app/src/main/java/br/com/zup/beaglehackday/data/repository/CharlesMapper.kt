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

package br.com.zup.beaglehackday.data.repository

import br.com.zup.beaglehackday.data.model.CharlesRequest
import br.com.zup.beaglehackday.data.model.CharlesResponse
import br.com.zup.beaglehackday.data.model.RequestData

private const val DEFAULT_CIRCLE_ID = "bbb35775-1f7a-4f72-8fb3-d21b7274fb6f"

object CharlesMapper {
    fun mapToCharlesRequest(username: String) = CharlesRequest(requestData = RequestData(username))

    fun mapToCircleResponse(charlesResponse: CharlesResponse) =
        charlesResponse.circles.firstOrNull()?.id ?: DEFAULT_CIRCLE_ID
}
