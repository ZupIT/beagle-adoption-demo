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

package br.com.zup.beaglehackday.robots

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import br.com.zup.beaglehackday.utils.WaitHelper
import org.hamcrest.Matchers

class ScreenRobot {

    fun checkViewIdIsDisplayed(id: Int): ScreenRobot {
        WaitHelper.waitForWithId(id)
        onView(ViewMatchers.withId(id)).check(
            matches(
                isDisplayed()
            )
        )
        return this
    }

    fun viewAndClick(id: Int): ScreenRobot {
        checkViewIdIsDisplayed(id)
        clickView(id)
        return this
    }

    private fun clickView(id: Int): ScreenRobot{
        onView(ViewMatchers.withId(id)).perform(ViewActions.click())
        return this
    }

    fun clickOnTextDialog(text: String): ScreenRobot {
        onView(withText(text)).inRoot(isDialog()).check(matches(isDisplayed())).perform(pressBack())
        return this
    }

    fun checkOnText(text: String): ScreenRobot {
        onView(Matchers.allOf(withText(text), isDisplayed()))
        return this
    }
}