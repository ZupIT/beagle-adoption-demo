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
