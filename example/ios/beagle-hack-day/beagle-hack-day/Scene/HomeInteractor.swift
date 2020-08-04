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

protocol HomeBusinessLogic {
    func loginCD(userName name: String)
}

class HomeInteractor {
    private var worker: HomeNetworkLogic?
    private var presenter: HomePresentationLogic?
    
    init(worker: HomeNetworkLogic, presenter: HomePresentationLogic) {
        self.worker = worker
        self.presenter = presenter
    }
}

//MARK: - HomeBusinessLogic
extension HomeInteractor: HomeBusinessLogic {
    
    func loginCD(userName name: String) {
        worker?.loginCD(with: name, completion: { [weak self] (result) in
            guard let self = self else { return }
            switch result {
            case .success:
                self.presenter?.presentLoginCDSuccess(response: .init(userName: name))
            case .failure:
                self.presenter?.presentLoginCDFailure(response: .init(userName: name))
            }
        })
    }
    
}