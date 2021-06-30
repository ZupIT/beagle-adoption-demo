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

import br.com.zup.beagle.annotation.BeaglePreview
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.PositionType
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.applyStyle
import br.com.zup.beagle.ext.setId
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
import br.com.zup.beagle.widget.layout.extensions.setId
import br.com.zup.beagle.widget.ui.Button
import br.com.zup.beagle.widget.ui.Image
import br.com.zup.beagle.widget.ui.ImagePath.Local
import br.com.zup.beaglehackday.enumerator.ImageType
import br.com.zup.beaglehackday.enumerator.SizeType
import br.com.zup.beaglehackday.widget.Color
import br.com.zup.beaglehackday.widget.ColorSelector
import br.com.zup.beaglehackday.widget.ImageDetail
import br.com.zup.beaglehackday.widget.SizeSelector

@BeaglePreview
fun livePreview() = OutfitScreen()

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
                ).setId("heartIcon"),
                NavigationBarItem(
                    text = "Bag Icon",
                    image = Local.justMobile("bag"),
                    action = Alert(
                        title = "Bag title",
                        message = "Bag message",
                        labelOk = "ok"
                    )
                ).setId("bagIcon")
            )
        )
    }

    private fun screenWidgets(): Widget {
        return Container(
            context = ContextData(
                id = "shirtData",
                value = ShirtData(id = "123123", price = "$23.99")
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
        ).setId("submitButton")
    }

    private fun outfitSize(): Widget {
        return SizeSelector(
            listOf(SizeType.XS, SizeType.S, SizeType.M, SizeType.L, SizeType.XL),
            height = 50.0
        ).applyStyle(
            Style(
                margin = EdgeValue(bottom = 10.unitReal())
            )
        ).setId("sizeSelector")
    }

    private fun buildSetContextFor(value: String): SetContext {
        return  SetContext("shirtData", path = "price", value = value)
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
            ),
            height = 60.0
        ).applyStyle(
            Style(
                margin = EdgeValue(top = 10.unitReal()),
                size = Size(height = UnitValue.real(80))
            )
        ).setId("colorSelector")
    }

    private fun outfitImage(): Widget {
        return Container(
            children = listOf(
                Image(
                    path = Local.justMobile("shirt"),
                    mode = ImageContentMode.CENTER
                ).applyStyle(
                    Style(cornerRadius = CornerRadius(20.0))
                ).setId("shirtImage"),
                ImageDetail(
                    value = expressionOf("@{shirtData.price}"),
                    image = ImageType.RED_HEART
                ).applyStyle(
                    Style(
                        padding = EdgeValue(bottom = 5.unitReal()),
                        margin = EdgeValue(horizontal = 10.unitReal()),
                        positionType = PositionType.ABSOLUTE
                    )
                ).setId("imageDetail")
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
        val id: String,
        val price: String
)