//
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

import Foundation

protocol HomePresentationLogic {
    func presentLoginCDSuccess(response: Home.LoginCD.Response)
    func presentLoginCDFailure(response: Home.LoginCD.Response)
}

class HomePresenter: HomePresentationLogic {
    private var viewController: HomeDisplayLogic?

    init(viewController: HomeDisplayLogic) {
        self.viewController = viewController
    }
    
    func presentLoginCDSuccess(response: Home.LoginCD.Response) {
        viewController?.displayLoginCD(viewModel:
            .init(message: """
                User name: \(response.userName)
                CD Login Succeeded!
                """
            )
        )
    }
    
    func presentLoginCDFailure(response: Home.LoginCD.Response) {
        viewController?.displayLoginCD(viewModel:
            .init(message: """
                User name: \(response.userName)
                CD Login Failed!
                """
            )
        )
    }
}
