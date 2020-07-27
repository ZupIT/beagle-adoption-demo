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

import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.PositionType
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.unitPercent
import br.com.zup.beagle.ext.unitReal
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Alert
import br.com.zup.beagle.widget.action.Navigate
import br.com.zup.beagle.widget.action.Route
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.core.*
import br.com.zup.beagle.widget.layout.*
import br.com.zup.beagle.widget.ui.Button
import br.com.zup.beagle.widget.ui.Image
import br.com.zup.beagle.widget.ui.ImagePath.Local
import br.com.zup.beaglehackday.enumerator.ImageType
import br.com.zup.beaglehackday.enumerator.SizeType
import br.com.zup.beaglehackday.widget.Color
import br.com.zup.beaglehackday.widget.ColorSelector
import br.com.zup.beaglehackday.widget.ImageDetail
import br.com.zup.beaglehackday.widget.SizeSelector

class OutfitScreen : ScreenBuilder {

    override fun build() =
        Screen(
            navigationBar = navBar(),
            child = screenWidgets()
        )

    private fun navBar(): NavigationBar {
        return NavigationBar(
            title = "Title",
            styleId = "customNavigation",
            navigationBarItems = listOf(
                NavigationBarItem(
                    text = "Heart Icon",
                    image = Local.justMobile("heart"),
                    action = Alert(
                        title = "Heart title",
                        message = "Heart message",
                        labelOk = "ok"
                    )
                ),
                NavigationBarItem(
                    text = "Bag Icon",
                    image = Local.justMobile("bag"),
                    action = Alert(
                        title = "Bag title",
                        message = "Bag message",
                        labelOk = "ok"
                    )
                )
            )
        )
    }

    private fun screenWidgets(): Widget {
        return Container(
            context = ContextData(
                id = "context",
                value = ShirtData(shirtId = "123123", shirtPrice = "$23.99")
            ),
            children = listOf(
                outfitImage(),
                outfitColors(),
                outfitSize(),
                submitButton()
            )
        ).applyStyle(
            Style(
                flex = Flex(
                    grow = 1.0,
                    justifyContent = JustifyContent.SPACE_BETWEEN
                ),
                padding = EdgeValue(
                    left = 30.unitReal(),
                    right = 30.unitReal(),
                    bottom = 30.unitReal()
                )
            )
        )
    }

    private fun submitButton(): Button {
        return Button(
            text = "Add to cart",
            styleId = "customButton",
            onPress = listOf(Navigate.PushView(Route.Remote("/detail")))
        )
    }

    private fun outfitSize(): Widget {
        return SizeSelector(
            listOf(SizeType.XS, SizeType.S, SizeType.M, SizeType.L, SizeType.XL)
        ).applyStyle(
            Style(
                margin = EdgeValue(bottom = 10.unitReal())
            )
        )
    }

    private fun buildSetContextFor(value: String): SetContext {
        return  SetContext("context", path = "shirtPrice", value = value)
    }

    private fun outfitColors(): Widget {
        return ColorSelector(
            colors = listOf(
                Color(
                    hex = "#ECECED",
                    onPress = buildSetContextFor(value = "$23.99")
                ),
                Color(
                    hex = "#D7D8DA",
                    onPress = buildSetContextFor(value = "$25.99")
                ),
                Color(
                    hex = "#394657",
                    onPress = buildSetContextFor(value = "$27.99")
                ),
                Color(
                    hex = "#B38B6D",
                    onPress = buildSetContextFor(value = "$29.99")
                )
            )
        ).applyStyle(
            Style(
                margin = EdgeValue(top = 10.unitReal())
            )
        )
    }

    private fun outfitImage(): Widget {
        return Container(
            children = listOf(
                Image(
                    path = Local.justMobile("shirt"),
                    mode = ImageContentMode.CENTER
                ).applyStyle(
                    Style(cornerRadius = CornerRadius(20.0))
                ),
                ImageDetail(
                    value = expressionOf("@{context.shirtPrice}"),
                    image = ImageType.RED_HEART
                ).applyStyle(
                    Style(
                        padding = EdgeValue(bottom = 5.unitReal()),
                        margin = EdgeValue(horizontal = 10.unitReal()),
                        positionType = PositionType.ABSOLUTE
                    )
                )
            )
        ).applyStyle(
            Style(
                flex = Flex(
                    justifyContent = JustifyContent.FLEX_END
                ),
                margin = EdgeValue(
                    left = 18.unitReal(),
                    right = 18.unitReal()
                ),
                size = Size(
                    height = 65.unitPercent()
                )
            )
        )
    }
}

data class ShirtData(
        val shirtId: String,
        val shirtPrice: String
)