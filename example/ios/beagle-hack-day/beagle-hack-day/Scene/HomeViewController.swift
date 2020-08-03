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

import Beagle

class HomeViewController: UIViewController {
        
    var charlesManager: CharlesManaging = CharlesManager()
    
    private var router: HomeRoutingLogic?
    
    override func loadView() {
        view = HomeView(delegate: self)
    }
    
    func setup(router: HomeRoutingLogic?) {
        self.router = router
    }
    
}

//MARK: - HomeViewActionsDelegate
extension HomeViewController: HomeViewActionsDelegate {
    
    func demoButtonTapped() {
        router?.routeToDemo()
    }
    
    func previewButtonTapped() {
        router?.routeToPreview()
    }
    
}


