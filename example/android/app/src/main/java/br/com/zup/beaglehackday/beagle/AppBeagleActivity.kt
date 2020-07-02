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

package br.com.zup.beaglehackday.beagle

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.view.BeagleActivity
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beaglehackday.R
import kotlinx.android.synthetic.main.activity_app_beagle.*

@BeagleComponent
class AppBeagleActivity : BeagleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_beagle)
    }

    override fun getServerDrivenContainerId() =
        R.id.containerServerDriven

    override fun getToolbar(): Toolbar = toolbarServerDriven

    override fun onServerDrivenContainerStateChanged(state: ServerDrivenState) {
        if (state is ServerDrivenState.Loading) {
            progressServerDriven.visibility = if (state.loading) View.VISIBLE else View.GONE
        } else if (state is ServerDrivenState.Error) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show()
        }
    }
}
