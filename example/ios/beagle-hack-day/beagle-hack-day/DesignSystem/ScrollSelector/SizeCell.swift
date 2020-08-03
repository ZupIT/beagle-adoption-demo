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

class SizeCell: UICollectionViewCell {
    
    //MARK: Views
    private lazy var sizeLabel: UILabel = {
        let view = UILabel()
        view.textColor = .black
        return view
    }()
    
    //MARK: Init
    override init(frame: CGRect) {
        super.init(frame: frame)
        setup()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    //MARK: Reuse ID
    private(set) static var reuseId = String(describing: SizeCell.self)
}

//MARK: - SelectorCell
extension SizeCell: SelectorCell {
    
    func setup(text: String, isSelected: Bool) {
        handleSelection(isSelected: isSelected)
        sizeLabel.text = text
    }
    
    private func handleSelection(isSelected: Bool) {
        backgroundColor = isSelected ? .lightGray : .clear
    }
    
}

//MARK: - ViewCode
extension SizeCell: ViewCode {
    
    func setupHierarchy() {
        addSubview(sizeLabel)
    }
    
    func setupConstraints() {
        sizeLabel.translatesAutoresizingMaskIntoConstraints = false
        sizeLabel.centerTo(self)
    }
    
    func additionalConfigurations() {
        clipsToBounds = true
        layer.cornerRadius = 20
    }
    
}
