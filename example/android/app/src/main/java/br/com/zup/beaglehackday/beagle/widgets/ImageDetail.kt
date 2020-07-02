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
        observeBindChanges(rootView, value) {
            tvDetail.text = it
        }
        getImageResourceByImageType(context)?.let {
            ivDetail.setImageResource(it)
        }
    }

    private fun getImageResourceByImageType(context: Context) : Int? {
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
