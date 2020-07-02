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

package br.com.zup.beaglehackday.controller

import br.com.zup.beaglehackday.builder.DetailScreen
import br.com.zup.beaglehackday.builder.OutfitScreen
import br.com.zup.beaglehackday.service.OutfitService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OutfitController(private val outfitService: OutfitService) {
    @GetMapping("/outfit")
    fun showOutfit(): OutfitScreen = outfitService.getOutfitScreen()

    @GetMapping("/detail")
    fun showDetail(): DetailScreen = outfitService.getDetailScreen()
}
