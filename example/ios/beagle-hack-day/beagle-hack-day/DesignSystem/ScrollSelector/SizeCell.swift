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

//MARK: - Color View

protocol SelectorCell {
    func setupCell(text: String, isSelected: Bool)
}

class SizeCell: UICollectionViewCell, SelectorCell {
    
    //MARK: View
    
    private lazy var sizeLabel: UILabel = {
        let view = UILabel()
        view.textColor = .black
        return view
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        buildViewLayout()
        setupViewLayer()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    //MARK: Reuse Id

    private(set) static var reuseId = String(describing: SizeCell.self)
    
    //MARK: Functions
    
    func setupCell(text: String, isSelected: Bool) {
        handleSelection(isSelected: isSelected)
        sizeLabel.text = text
    }
    
    private func handleSelection(isSelected: Bool) {
        backgroundColor = isSelected ? .lightGray : .clear
    }
    
    private func buildViewLayout() {
        addSubview(sizeLabel)
        sizeLabel.translatesAutoresizingMaskIntoConstraints = false
        sizeLabel.centerTo(self)
    }
    
    private func setupViewLayer() {
        clipsToBounds = true
        layer.cornerRadius = 20
    }
}
