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

class BeagleConfig {
    static let dependencies = BeagleDependencies()
    static let client = NetworkClientDefault(dependencies: dependencies)
    
    private init() {}

    static func setup() {
        BeagleConfig.registerValidatorProviders(in: dependencies)
        BeagleConfig.setAppTheme(in: dependencies)
        BeagleConfig.setNetworkClient(in: dependencies)
        let innerDependencies = InnerDependencies()
        dependencies.networkClient = NetworkClientDefault(dependencies: innerDependencies)
        dependencies.cacheManager = CacheManagerDefault(dependencies: innerDependencies)
        dependencies.logger = innerDependencies.logger
        dependencies.urlBuilder = UrlBuilder(baseUrl: URL(string: Constants.Server.baseURL))
        Beagle.dependencies = dependencies
        
        BeagleConfig.registerWidgets()
    }
    
    /// Setup Beagle Network Client, setting Charles circle id header
    static private func setNetworkClient(in dependencies: BeagleDependencies) {
        client.httpRequestBuilder.additionalHeaders = ["x-circle-id":"578640d5-50af-41d4-817c-4639f80c207d"]
        dependencies.networkClient = client
    }
    
    /// Register custom widgets
    static private func registerWidgets() {
        Beagle.registerCustomComponent("imageDetail", componentType: ImageDetail.self)
        Beagle.registerCustomComponent("colorSelector", componentType: ColorSelector.self)
        Beagle.registerCustomComponent("sizeSelector", componentType: SizeSelector.self)
    }
    
    /// Register styles
    static func setAppTheme(in dependencies: BeagleDependencies) {
        let theme = AppTheme(styles: [
            "customButton" : Styles.primaryButton,
            "customNavigation" : Styles.customNavigationBar
        ])
        
        dependencies.theme = theme
    }
    
    /// Register validator providers
    static private func registerValidatorProviders(in dependencies: BeagleDependencies) {
        let provider = ValidatorProviding()
        provider["exampleProvider"] = {
            ($0 as? Int) ?? 0 > 0
        }
        dependencies.validatorProvider = provider
    }
}

class InnerDependencies: DependencyLogger {
    var logger: BeagleLoggerType = BeagleLoggerDefault()
}
