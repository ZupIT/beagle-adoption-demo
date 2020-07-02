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

package br.com.zup.beaglehackday.beagle

import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.setup.DesignSystem
import br.com.zup.beaglehackday.R

@BeagleComponent
class AppDesignSystem : DesignSystem() {

    override fun buttonStyle(id: String) = when(id) {
        "customButton" -> R.style.DefaultButton_CustomButton
        else -> R.style.DefaultButton
    }

    override fun image(id: String) = when (id) {
            "back" -> R.drawable.ic_back
            "bag" -> R.drawable.ic_bag
            "check" -> R.drawable.ic_check
            "red_heart" -> R.drawable.ic_red_heart
            "heart" -> R.drawable.ic_heart
            "plus" -> R.drawable.ic_plus
            "question" -> R.drawable.ic_question
            "shopping" -> R.drawable.ic_shopping
            "shirt" -> R.drawable.tshirt
            else -> android.R.drawable.ic_menu_help
    }

    override fun textStyle(id: String) = when (id) {
        "boldStyle" -> R.style.DefaultTextAppearance_CustomText
        else -> R.style.DefaultTextAppearance
    }

    override fun toolbarStyle(id: String) = when (id) {
        "customNavigation" -> R.style.DefaultToolbar_CustomToolbar
        else -> R.style.DefaultToolbar
    }
}
