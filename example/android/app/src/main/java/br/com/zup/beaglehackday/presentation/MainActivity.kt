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
