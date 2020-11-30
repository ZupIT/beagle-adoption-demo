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

package br.com.zup.beaglehackday.builder

import br.com.zup.beagle.ext.setId
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Alert
import br.com.zup.beagle.widget.core.TextAlignment
import br.com.zup.beagle.widget.layout.*
import br.com.zup.beagle.widget.layout.extensions.setId
import br.com.zup.beagle.widget.ui.ImagePath
import br.com.zup.beagle.widget.ui.Text

class DetailScreen : ScreenBuilder {

    override fun build() = Screen(
        navigationBar = navBar(),
        child = screenWidgets()
    )

    private fun navBar(): NavigationBar {
        return NavigationBar(
            title = "Detail",
            styleId = "customNavigation",
            navigationBarItems = listOf(
                NavigationBarItem(
                    text = "Heart Icon",
                    image = ImagePath.Local.justMobile("heart"),
                    action = Alert(
                        title = "Heart title",
                        message = "Heart message",
                        labelOk = "ok"
                    )
                ).setId("heartIconDetail")
            )
        )
    }

    private fun screenWidgets(): Widget {
        return Container(
            children = listOf(
                Text("This navigation is awesome!", alignment = TextAlignment.CENTER).setId("textDetail")
            )
        )
    }
}
