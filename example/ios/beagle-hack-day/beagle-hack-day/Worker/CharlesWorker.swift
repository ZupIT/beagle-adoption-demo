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
import BeagleDefaults

protocol CharlesWorkLogic {
    func login(with username: String, completion: @escaping (CharlesWorkerResult) -> Void)
}

enum CharlesWorkerResult {
    case success
    case failure(error: Error)
}

class CharlesWorker: CharlesWorkLogic {
    func login(with username: String, completion: @escaping (CharlesWorkerResult) -> Void) {
        struct Response: Decodable {
            let circles: [Circles]
            
            struct Circles: Decodable {
                let id: String
                let name: String
            }
        }
        
        let session = URLSession.shared
        
        session.dataTask(with: buildCharlesCircleIdRequest(username: username)) { (data, response, error) in
            guard let data = data, error == nil else {
                if let error = error {
                    completion(.failure(error: error))
                }
                return
            }
            
            let response = try? JSONDecoder().decode(Response.self, from: data)
            if let id = response?.circles[0].id {
                BeagleConfig.client.httpRequestBuilder.additionalHeaders = ["x-circle-id":id]
            }
            DispatchQueue.main.async {
                completion(.success)
            }
            
        }.resume()
    }
    
    private func buildCharlesCircleIdRequest(username: String) -> URLRequest {
        let data = """
        {
            "workspaceId": "2ec54c86-4d77-4053-b3ed-3692db22e794",
            "requestData": {
                "username": "\(username)"
            }
        }
        """.data(using: .utf8)
        
        var request = URLRequest(url: URL(string: "https://charles-prod.continuousplatform.com/charlescd-circle-matcher/identify")!, cachePolicy: .reloadIgnoringLocalAndRemoteCacheData, timeoutInterval: 100)
        request.allHTTPHeaderFields = ["Content-Type": "application/json"]
        request.httpMethod = "POST"
        request.httpBody = data
        
        return request
    }
}
