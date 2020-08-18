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

class BackgroundView: UIView {
    
    // MARK: -
    public enum Direction {
        case horizontal
        case vertical
        
        fileprivate var points: (start: CGPoint, end: CGPoint) {
            switch self {
            case .horizontal:
                return (start: CGPoint(x: 0, y: 0.5), end: CGPoint(x: 1, y: 0.5))
            case .vertical:
                return (start: CGPoint(x: 0.5, y: 0), end: CGPoint(x: 0.5, y: 1))
            }
        }
    }
    
    public enum Corner {
        case pill
        case radius(value: CGFloat)
    }
    
    public struct Border {
        public var color: UIColor? = nil
        public var width: CGFloat = 0
    }
    
    // MARK: -
    override init(frame: CGRect) {
        self.colors = []
        self.direction = .vertical
        self.corner = .radius(value: 0)
        self.border = Border()
        super.init(frame: frame)
    }
    
    required init?(coder: NSCoder) {
        self.colors = []
        self.direction = .vertical
        self.corner = .radius(value: 0)
        self.border = Border()
        super.init(coder: coder)
    }
    
    // MARK: -
    public var colors: [UIColor] {
        didSet {
            gradientLayer?.colors = colors.map { $0.cgColor }
        }
    }
    
    public var direction: Direction {
        didSet {
            let points = direction.points
            gradientLayer?.startPoint = points.start
            gradientLayer?.endPoint = points.end
        }
    }
    
    public var corner: Corner {
        didSet { setNeedsLayout() }
    }
    
    public var border: Border {
        didSet {
            layer.borderColor = border.color?.cgColor
            layer.borderWidth = border.width
        }
    }
    
    // MARK: -
    override class var layerClass: AnyClass {
        return CAGradientLayer.self
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        switch corner {
        case .pill:
            layer.cornerRadius = bounds.height / 2
        case .radius(let value):
            layer.cornerRadius = value
        }
    }
    
    private var gradientLayer: CAGradientLayer? {
        return layer as? CAGradientLayer
    }
    
}
