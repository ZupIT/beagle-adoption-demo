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

protocol HomeViewActionsDelegate {
    func demoButtonTapped()
    func previewButtonTapped()
}

class HomeView: UIView {
    
    private var delegate: HomeViewActionsDelegate?
    
    private lazy var beagleImage: UIImageView = {
        let image = UIImageView()
        image.image = UIImage(named: "beagle_logo")
        image.contentMode = .scaleAspectFit
        return image
    }()
    
    private lazy var livePreviewButton: UIButton = {
        let button = UIButton()
        Styles.zupPrimaryButton()(button)
        button.setTitle("Live Preview", for: .normal)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()

    private lazy var previewButton: UIButton = {
        let button = UIButton()
        Styles.zupSecondaryButton()(button)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("Demo", for: .normal)
        return button
    }()
    
    init(delegate: HomeViewActionsDelegate) {
        super.init(frame: .zero)
        self.delegate = delegate
        setup()
    }
    
    @available(*, unavailable)
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
}

//MARK: - ViewCode
extension HomeView: ViewCode {
    func setupHierarchy() {
        addSubview(beagleImage)
        addSubview(livePreviewButton)
        addSubview(previewButton)
    }
    
    func setupConstraints() {
        beagleImage.anchor(top: safeAreaLayoutGuide.topAnchor, left: leftAnchor, right: rightAnchor, topConstant: 20, leftConstant: 20, rightConstant: 20)
        
        livePreviewButton.anchor(top: beagleImage.bottomAnchor, left: leftAnchor, right: rightAnchor, topConstant: 100, leftConstant: 20, rightConstant: 20, heightConstant: 50)
        
        previewButton.anchor(top: livePreviewButton.bottomAnchor, left: leftAnchor, right: rightAnchor, topConstant: 50, leftConstant: 20, rightConstant: 20, heightConstant: 50)
    }
    
    func additionalConfigurations() {
        setupActions()
        backgroundColor = .white
    }
    
}

//MARK: - Actions
extension HomeView {
    private func setupActions() {
        previewButton.addTarget(self, action: #selector(handleDemoButtonTap), for: .touchUpInside)
        livePreviewButton.addTarget(self, action: #selector(handlePreviewButtonTap), for: .touchUpInside)
    }
    
    @objc
    private func handlePreviewButtonTap() {
        delegate?.previewButtonTapped()
    }
    
    @objc
    private func handleDemoButtonTap() {
        delegate?.demoButtonTapped()
    }
}
