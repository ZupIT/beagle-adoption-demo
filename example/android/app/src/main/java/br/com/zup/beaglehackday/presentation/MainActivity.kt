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

package br.com.zup.beaglehackday.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.zup.beagle.android.preview.PreviewActivity
import br.com.zup.beagle.android.view.BeagleActivity
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beaglehackday.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MainViewModel by viewModels()

        observeResponse(viewModel)

        btPreview.setOnClickListener {
            startActivity(PreviewActivity.newIntent(this, endpoint = etPreview.text.toString()))
        }

        btDemo.setOnClickListener {
            //viewModel.getCircle("circleId")
            startActivity(BeagleActivity.newIntent(this, ScreenRequest(url = "/outfit")))
        }
    }

    private fun observeResponse(viewModel: MainViewModel) {
        viewModel.response.observe(this, Observer {
            when (it) {
                is Response.Loading ->
                    if (it.loading) {
                        btDemo.isEnabled = false
                        btDemo.alpha = 0.5f
                        pbLoading.visibility = View.VISIBLE
                    } else {
                        btDemo.isEnabled = true
                        btDemo.alpha = 1f
                        pbLoading.visibility = View.GONE
                    }
                is Response.Success -> {
                    startActivity(
                        BeagleActivity.newIntent(
                            this,
                            ScreenRequest(
                                headers = mapOf("x-circle-id" to it.circleId),
                                url = "/outfit"
                            )
                        )
                    )
                }
                is Response.Error -> Toast.makeText(this, it.exception, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
