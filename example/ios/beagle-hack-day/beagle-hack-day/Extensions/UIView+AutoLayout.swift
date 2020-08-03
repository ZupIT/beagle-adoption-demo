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

import UIKit

extension UIView {
    
    func anchorTo(_ superview: UIView) {
        [topAnchor.constraint(equalTo: superview.topAnchor),
         leftAnchor.constraint(equalTo: superview.leftAnchor),
         bottomAnchor.constraint(equalTo: superview.bottomAnchor),
         rightAnchor.constraint(equalTo: superview.rightAnchor)
        ].forEach { $0.isActive = true }
    }
    
    func centerTo(_ view: UIView) {
        [centerXAnchor.constraint(equalTo: view.centerXAnchor),
         centerYAnchor.constraint(equalTo: view.centerYAnchor)
        ].forEach { $0.isActive = true}
    }
    
}

