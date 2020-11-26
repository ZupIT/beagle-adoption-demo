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

package br.com.zup.beaglehackday

import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.zup.beagle.android.utils.toAndroidId
import br.com.zup.beaglehackday.robots.ScreenRobot
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ScreenOutfitInstrumentedTest: BaseScreenInstrumentedTests() {

    @Before
    fun setup() {
        starActivity("/outfit")
    }

    @Test
    fun button_validation_is_being_rendered(){
        ScreenRobot()
                .viewAndClick("submitButton".toAndroidId())
    }

    @Test
    fun image_shirt_validation_is_being_rendered(){
        ScreenRobot()
                .checkViewIdIsDisplayed("shirtImage".toAndroidId())
    }

    @Test
    fun image_detail_validation_is_being_rendered(){
        ScreenRobot()
                .checkViewIdIsDisplayed("imageDetail".toAndroidId())
    }

    @Test
    fun colorSelector_validation_is_being_rendered(){
        ScreenRobot()
                .checkViewIdIsDisplayed("colorSelector".toAndroidId())
    }

    @Test
    fun sizeSelector_validation_is_being_rendered(){
        ScreenRobot()
                .checkViewIdIsDisplayed("sizeSelector".toAndroidId())
    }

    @Test
    fun navigationBar_heart_icon_validation_is_being_rendered_and_click(){
        ScreenRobot()
                .viewAndClick("heartIcon".toAndroidId())
                .clickOnTextDialog("OK")
    }

    @Test
    fun navigationBar_bag_icon_validation_is_being_rendered_and_click(){
        ScreenRobot()
                .viewAndClick("bagIcon".toAndroidId())
                .clickOnTextDialog("OK")
    }
}
