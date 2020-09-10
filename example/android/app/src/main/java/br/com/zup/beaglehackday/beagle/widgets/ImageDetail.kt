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

package br.com.zup.beaglehackday.beagle.widgets

import android.content.Context
import br.com.zup.beagle.android.context.Bind
import br.com.zup.beagle.android.utils.observeBindChanges
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beaglehackday.components.ImageDetailView
import kotlinx.android.synthetic.main.view_image_detail.view.*

enum class ImageType {
    RED_HEART, SHOPPING
}

@RegisterWidget
data class ImageDetail(
    val value: Bind<String>,
    val image: ImageType
) : WidgetView() {

    override fun buildView(rootView: RootView) = ImageDetailView(rootView.getContext()).apply {
        observeBindChanges(rootView, this, value) {
            tvDetail.text = it
        }
        getImageResourceByImageType(context)?.let {
            ivDetail.setImageResource(it)
        }
    }

    private fun getImageResourceByImageType(context: Context): Int? {
        val resourceId = context.resources.getIdentifier(
            "ic_${image.name}",
            "drawable",
            context.packageName
        )
        return if (resourceId != 0) {
            resourceId
        } else {
            null
        }
    }
}
