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

class ColorCell: UICollectionViewCell {
    
    //MARK: Views
    private lazy var selectedImage: UIImageView = {
        let view = UIImageView()
        view.image = UIImage.dsCheck
        view.tintColor = .black
        view.isUserInteractionEnabled = false
        return view
    }()
    
    //MARK: Init
    override init(frame: CGRect) {
        super.init(frame: frame)
        setup()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    //MARK: Reuse ID
    static let reuseId = String(describing: ColorCell.self)
}

//MARK: - SelectorCell
extension ColorCell: SelectorCell {
    
    func setup(text: String, isSelected: Bool) {
        handleSelection(color: text, isSelected: isSelected)
    }
    
    private func handleSelection(color: String, isSelected: Bool) {
        backgroundColor = isSelected ? .white : UIColor(hex: color)
        selectedImage.isHidden = !isSelected
        layer.borderColor = UIColor(hex: color)?.cgColor
    }
    
}

//MARK: - ViewCode
extension ColorCell: ViewCode {
    
    func setupHierarchy() {
        addSubview(selectedImage)
    }
    
    func setupConstraints() {
        selectedImage.translatesAutoresizingMaskIntoConstraints = false
        selectedImage.widthAnchor.constraint(equalToConstant: 10).isActive = true
        selectedImage.heightAnchor.constraint(equalToConstant: 10).isActive = true
        selectedImage.centerTo(self)
    }
    
    func additionalConfigurations() {
        clipsToBounds = true
        layer.cornerRadius = 20
        layer.borderWidth = 2
    }
    
}
