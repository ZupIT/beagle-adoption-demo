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

protocol HomeRoutingLogic {
    func routeToDemo()
    func routeToPreview()
}

class HomeRouter: HomeRoutingLogic {
    private weak var viewController: UIViewController?
    
    init(viewController: UIViewController) {
        self.viewController = viewController
    }
    
    func routeToDemo() {
        guard let vc = viewController else { return }
        let controller = BeagleScreenViewController(
            .remote(.init(url: Constants.Server.Path.outfit))
        )
        vc.navigationController?.pushViewController(controller, animated: true)
    }
    
    func routeToPreview() {
        guard let vc = viewController else { return }
        BeaglePreview.present(in: vc)
    }
}
