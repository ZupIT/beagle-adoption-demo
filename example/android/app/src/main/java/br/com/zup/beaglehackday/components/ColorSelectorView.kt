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

package br.com.zup.beaglehackday.components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.view.children
import br.com.zup.beaglehackday.R
import kotlinx.android.synthetic.main.view_color_selector.view.*
import kotlinx.android.synthetic.main.view_item.view.*

class ColorSelectorView(context: Context) : LinearLayout(context) {

    init {
        View.inflate(context, R.layout.view_color_selector, this)
    }

    fun setItem(color: String, onPress: () -> Unit) {
        llColor.addView(createItem(color, onPress))
    }

    private fun createItem(color: String, onPress: () -> Unit): View {
        val view = LayoutInflater.from(context).inflate(R.layout.view_item, llColor, false)
        view.background = changeDrawableColor(color)
        view.setOnClickListener {
            removeSelected()
            view.ivItem.visibility = View.VISIBLE
            onPress.invoke()
        }
        return view
    }

    private fun removeSelected() {
        llColor.children.forEach {
            it.ivItem.visibility = View.GONE
        }
    }

    private fun changeDrawableColor(color: String): Drawable? {
        val drawable = AppCompatResources.getDrawable(context, R.drawable.bg_color)
        val coloredDrawable = drawable?.let { DrawableCompat.wrap(it) }
        coloredDrawable?.let { DrawableCompat.setTint(it, Color.parseColor(color)) }
        return coloredDrawable
    }
}
