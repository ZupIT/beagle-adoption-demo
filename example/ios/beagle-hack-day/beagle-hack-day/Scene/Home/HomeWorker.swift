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

protocol HomeWorkLogic {
    func loginCD(
        with username: String,
        completion: @escaping (HomeWorkerResult) -> Void
    )
}

enum HomeWorkerError: Error {
    case loginCDFail
}

enum HomeWorkerResult {
    case success
    case failure(error: HomeWorkerError)
}

class HomeWorker: HomeWorkLogic {
    private var cdWorker: CharlesWorkLogic?
    
    init(cdWorker: CharlesWorkLogic = CharlesWorker()) {
        self.cdWorker = cdWorker
    }
    
    func loginCD(
        with username: String,
        completion: @escaping (HomeWorkerResult) -> Void
    ) {
        cdWorker?.login(with: username, completion: { (result) in
            switch result {
            case .success:
                completion(.success)
            case .failure:
                completion(.failure(error: .loginCDFail))
            }
        })
    }
}
