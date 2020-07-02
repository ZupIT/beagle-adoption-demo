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
import Beagle

class Styles {
    
    static func h1() -> (UILabel?) -> Void {
        BeagleStyle.label(withFont: .systemFont(ofSize: 24))
    }
    
    static func h2() -> (UILabel?) -> Void {
        BeagleStyle.label(withFont: .systemFont(ofSize: 16))
    }
    
    static func h3() -> (UILabel?) -> Void {
        BeagleStyle.label(withFont: .systemFont(ofSize: 12))
    }
    
    static func primaryButton() -> (UIButton?) -> Void {
        BeagleStyle.button(withTitleColor: .white)
            <> {
                $0?.setImage(UIImage(systemName: "plus"), for: UIControl.State.normal)
                $0?.tintColor = .white
                $0?.backgroundColor = .black
                $0?.contentEdgeInsets = UIEdgeInsets(top: 15, left: 0, bottom: 15, right: 0)
                Styles.h2()($0?.titleLabel)
                $0?.layer.masksToBounds = false
                $0?.layer.cornerRadius = 25
        }
    }
    
    static func customNavigationBar() -> (UINavigationBar?) -> Void {
        return {
            $0?.isTranslucent = false
            $0?.setValue(true, forKey: "hidesShadow")
            $0?.tintColor = .black
        }
    }
    
    static func zupPrimaryButton() -> (UIButton?) -> Void {
        gradientBackground(colors: [#colorLiteral(red: 0.5985979438, green: 0.7665129304, blue: 0.34283638, alpha: 1),#colorLiteral(red: 0.397660166, green: 0.7198082805, blue: 0.5657112598, alpha: 1)], direction: .horizontal, corner: .pill)
        <> BeagleStyle.button(withTitleColor: .white)
            <> {
                $0?.tintColor = .white
                Styles.h2()($0?.titleLabel)
        }
    }
    
    static func zupSecondaryButton() -> (UIButton?) -> Void {
        gradientBackground(colors: [#colorLiteral(red: 0.8709219694, green: 0.3171179891, blue: 0.20386675, alpha: 1),#colorLiteral(red: 0.9195727706, green: 0.5976730585, blue: 0.2411729395, alpha: 1)], direction: .horizontal, corner: .pill)
        <> BeagleStyle.button(withTitleColor: .white)
            <> {
                $0?.tintColor = .white
                Styles.h2()($0?.titleLabel)
        }
    }
    
    private static func gradientBackground(
        colors: [UIColor],
        direction: BackgroundView.Direction = .horizontal,
        corner: BackgroundView.Corner = .radius(value: 0),
        border: BackgroundView.Border = BackgroundView.Border()
    ) -> (UIView?) -> Void {
        return {
            guard let view = $0 else {
                return
            }
            let gradient = BackgroundView(frame: view.bounds)
            gradient.colors = colors
            gradient.direction = direction
            gradient.corner = corner
            gradient.border = border
            
            gradient.autoresizingMask = [.flexibleWidth, .flexibleHeight]
            gradient.translatesAutoresizingMaskIntoConstraints = true
            view.insertSubview(gradient, at: 0)
            
            gradient.isUserInteractionEnabled = false
            gradient.isAccessibilityElement = false
        }
    }
}
