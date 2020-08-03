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
import Beagle

class ScrollSelector: UIView {

    // MARK: Views
    private lazy var collectionView: UICollectionView = {
        return makeCollectionView()
    }()
    
    private lazy var label: UILabel = {
        let view = UILabel()
        view.textColor = .lightGray
        view.text = type.getDescription()
        return view
    }()
    
    private lazy var questionMarkIcon: UIImageView = {
        let imageView = UIImageView()
        imageView.translatesAutoresizingMaskIntoConstraints = false
        imageView.image = UIImage.dsQuestionMark
        imageView.tintColor = .lightGray
        return UIImageView()
    }()
    
    // MARK: Properties
    var onItemPress: ((_ index: Int) -> ())?
    
    private let scrollSelectorPreferedHeight: CGFloat = 60
    private var type: SelectorType
    private var selectedIndex = 0 {
        didSet {
            onItemPress?(selectedIndex)
        }
    }
    
    // MARK: Init
    init(selectorType: SelectorType) {
        self.type = selectorType
        super.init(frame: .zero)
        setup()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func sizeThatFits(_ size: CGSize) -> CGSize {
        .init(width: size.width, height: scrollSelectorPreferedHeight)
    }
    
    // MARK: Functions
    private func insertQuestionMarkIcon() {
        addSubview(questionMarkIcon)
        [questionMarkIcon.heightAnchor.constraint(equalToConstant: 20),
         questionMarkIcon.widthAnchor.constraint(equalToConstant: 20),
         questionMarkIcon.leftAnchor.constraint(equalTo: label.rightAnchor, constant: 10),
         questionMarkIcon.centerYAnchor.constraint(equalTo: centerYAnchor)
        ].forEach { $0.isActive = true }
    }
    
}

//MARK: Beagle InputValue
extension ScrollSelector: InputValue {
    
    func getValue() -> Any {
        type.getSelectedValue(index: selectedIndex)
    }
    
}

//MARK: - UICollectionViewDelegateFlowLayout, UICollectionViewDataSource
extension ScrollSelector: UICollectionViewDelegateFlowLayout, UICollectionViewDataSource {
    
    private func makeCollectionView() -> UICollectionView {
        let collectionView = UICollectionView(frame: .zero,
                                              collectionViewLayout: makeCollectionViewFlowLayout())
        collectionView.delegate = self
        collectionView.dataSource = self
        collectionView.showsHorizontalScrollIndicator = false
        collectionView.clipsToBounds = true
        collectionView.layer.cornerRadius = 30
        collectionView.backgroundColor = .clear
        return collectionView
    }
    
    private func makeCollectionViewFlowLayout() -> UICollectionViewFlowLayout {
        let flowLayout = UICollectionViewFlowLayout()
        flowLayout.scrollDirection = .horizontal
        flowLayout.itemSize = .init(width: 40, height: 40)
        return flowLayout
    }
    
    private func registerCells() {
        collectionView.register(ColorCell.self, forCellWithReuseIdentifier: ColorCell.reuseId)
        collectionView.register(SizeCell.self, forCellWithReuseIdentifier: SizeCell.reuseId)
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        type.itens().count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: type.reuseId(), for: indexPath)
        (cell as? SelectorCell)?.setup(text: type.itens()[indexPath.row], isSelected: indexPath.row == selectedIndex)
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let previousIndex = selectedIndex
        selectedIndex = indexPath.row
        collectionView.reloadItems(at: [IndexPath(row: previousIndex, section: indexPath.section), indexPath])
        collectionView.scrollToItem(at: indexPath, at: .centeredHorizontally, animated: true)
    }
    
}

//MARK: - ViewCode
extension ScrollSelector: ViewCode {
    
    func setupHierarchy() {
        addSubview(label)
        addSubview(collectionView)
    }
    
    func setupConstraints() {
        [collectionView.heightAnchor.constraint(equalToConstant: scrollSelectorPreferedHeight),
         collectionView.rightAnchor.constraint(equalTo: rightAnchor),
         collectionView.leftAnchor.constraint(equalTo: leftAnchor, constant: 150),
         collectionView.centerYAnchor.constraint(equalTo: centerYAnchor)
        ].forEach { $0.isActive = true }
        
        [label.leftAnchor.constraint(equalTo: leftAnchor),
         label.centerYAnchor.constraint(equalTo: centerYAnchor)
        ].forEach { $0.isActive = true }
    }
    
    func additionalConfigurations() {
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        label.translatesAutoresizingMaskIntoConstraints = false
        
        if case SelectorType.size = type {
            insertQuestionMarkIcon()
        }
        
        registerCells()
        collectionView.reloadData()
    }
    
}

