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

package br.com.zup.beaglehackday.beagle.config

import android.util.Log
import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.logger.BeagleLogger

private const val BEAGLE_TAG = "BeagleSDK"

@BeagleComponent
class BeagleLoggerDefault : BeagleLogger {

    override fun warning(message: String) {
        Log.w(BEAGLE_TAG, message)
    }

    override fun error(message: String) {
        Log.e(BEAGLE_TAG, message)
    }

    override fun error(message: String, throwable: Throwable) {
        Log.e(BEAGLE_TAG, message, throwable)
    }

    override fun info(message: String) {
        Log.i(BEAGLE_TAG, message)
    }

}