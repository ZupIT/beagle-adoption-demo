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
    func loginCDButtonTapped()
}

class HomeView: UIView {
    private var delegate: HomeViewActionsDelegate?
    
    //MARK: Views
    private lazy var stackView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .vertical
        stackView.distribution = .fillEqually
        stackView.spacing = 20.0
        return stackView
    }()
    
    private lazy var livePreviewButton: UIButton = {
        let button = UIButton()
        Styles.zupPrimaryButton()(button)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("live preview", for: .normal)
        return button
    }()
    
    private lazy var demoButton: UIButton = {
        let button = UIButton()
        Styles.zupSecondaryButton()(button)
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("demo", for: .normal)
        return button
    }()
    
    private lazy var loginCDButton: UIButton = {
        let button = UIButton()
        button.isEnabled = false
        
        button.translatesAutoresizingMaskIntoConstraints = false
        button.setTitle("Charles C.D.", for: .normal)
        
        button.setTitleColor(.gray, for: .disabled)
        button.isEnabled ? Styles.zupPrimaryButton()(button) : Styles.disabledButton()(button)
        return button
    }()
    
    //MARK: Init
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
        stackView.addArrangedSubview(livePreviewButton)
        stackView.addArrangedSubview(demoButton)

        addSubview(stackView)
    }
    
    func setupConstraints() {
        stackView.anchor(
            top: safeAreaLayoutGuide.topAnchor,
            left: leftAnchor,
            bottom: safeAreaLayoutGuide.bottomAnchor,
            right: rightAnchor,
            heightConstant: UIScreen.main.bounds.height
        )
    }
    
    func additionalConfigurations() {
        setupActions()
        backgroundColor = .white
    }

}

//MARK: - Actions
extension HomeView {
    private func setupActions() {
        demoButton.addTarget(
            self,
            action: #selector(handleDemoButtonTap),
            for: .touchUpInside
        )
        
        livePreviewButton.addTarget(
            self,
            action: #selector(handlePreviewButtonTap),
            for: .touchUpInside
        )
        
        loginCDButton.addTarget(
            self,
            action: #selector(handleLoginCDButtonTap),
            for: .touchUpInside
        )
    }
    
    @objc
    private func handleDemoButtonTap() {
        delegate?.demoButtonTapped()
    }
    
    @objc
    private func handlePreviewButtonTap() {
        delegate?.previewButtonTapped()
    }
    
    @objc
    private func handleLoginCDButtonTap() {
        delegate?.loginCDButtonTapped()
    }
}
