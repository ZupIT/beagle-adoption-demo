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

class ImageDetails: UIView {
    //MARK: Views
    private lazy var label: UILabel = {
        let label = UILabel()
        label.text = value
        label.textColor = .black
        label.backgroundColor = .white
        label.textAlignment = .center
        label.clipsToBounds = true
        label.layer.cornerRadius = 15
        return label
    }()
    
    private lazy var circleView: UIView = {
        let view = UIView()
        view.backgroundColor = .white
        view.clipsToBounds = true
        view.layer.cornerRadius = 15
        return view
    }()
    
    private lazy var heartView: UIImageView = {
        let imageView = UIImageView()
        imageView.image = UIImage(named: image)
        imageView.tintColor = .red
        return imageView
    }()
    
    //MARK: Properties
    var value: String {
        didSet {
            label.text = value
        }
    }
    
    let image: String
    
    //MARK: Init
    init(value: String = String(), image: String) {
        self.value = value
        self.image = image
        super.init(frame: .zero)
        setup()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func sizeThatFits(_ size: CGSize) -> CGSize {
        .init(width: size.width, height: 40)
    }
}

//MARK: - ViewCode
extension ImageDetails: ViewCode {
    func setupHierarchy() {
        addSubview(label)
        addSubview(circleView)
        circleView.addSubview(heartView)
    }
    
    func setupConstraints() {
        label.anchor(left: leftAnchor, widthConstant: 100, heightConstant: 30)
        label.anchorCenterYToSuperview()
        circleView.anchor(right: rightAnchor, widthConstant: 30, heightConstant: 30)
        circleView.anchorCenterYToSuperview()
        heartView.anchor(widthConstant: 10, heightConstant: 10)
        heartView.anchorCenterSuperview()
    }
}
