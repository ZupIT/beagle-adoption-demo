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
