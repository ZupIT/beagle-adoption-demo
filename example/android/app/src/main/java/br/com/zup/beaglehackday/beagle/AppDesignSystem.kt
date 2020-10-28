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

package br.com.zup.beaglehackday.beagle

import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.setup.DesignSystem
import br.com.zup.beaglehackday.R

@BeagleComponent
class AppDesignSystem : DesignSystem() {

    override fun buttonStyle(id: String) = when (id) {
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
