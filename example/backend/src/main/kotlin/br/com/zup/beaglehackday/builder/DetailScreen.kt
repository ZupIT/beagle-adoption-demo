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

package br.com.zup.beaglehackday.builder

import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Alert
import br.com.zup.beagle.widget.core.TextAlignment
import br.com.zup.beagle.widget.layout.*
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
                )
            )
        )
    }

    private fun screenWidgets(): Widget {
        return Container(
            children = listOf(
                Text("This navigation is awesome!", alignment = TextAlignment.CENTER)
            )
        )
    }
}
