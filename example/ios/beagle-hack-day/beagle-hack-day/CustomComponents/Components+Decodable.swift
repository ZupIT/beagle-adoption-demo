// Generated using Sourcery 0.18.0 — https://github.com/krzysztofzablocki/Sourcery
// DO NOT EDIT

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

// MARK: Color Decodable
extension Color {
    enum CodingKeys: String, CodingKey {
        case hex
        case onPress
    }

    internal init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)

        hex = try container.decode(String.self, forKey: .hex)
        onPress = try container.decode(forKey: .onPress)
    }
}

// MARK: ColorSelector Decodable
extension ColorSelector {
    enum CodingKeys: String, CodingKey {
        case colors
        case height
    }

    internal init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)

        widgetProperties = try WidgetProperties(from: decoder)
        colors = try container.decode([Color].self, forKey: .colors)
        height = try container.decode(Float.self, forKey: .height)
    }
}

// MARK: ImageDetail Decodable
extension ImageDetail {
    enum CodingKeys: String, CodingKey {
        case value
        case image
    }

    internal init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)

        widgetProperties = try WidgetProperties(from: decoder)
        value = try container.decode(Expression<String>.self, forKey: .value)
        image = try container.decode(String.self, forKey: .image)
    }
}

// MARK: SizeSelector Decodable
extension SizeSelector {
    enum CodingKeys: String, CodingKey {
        case sizes
        case height
    }

    internal init(from decoder: Decoder) throws {
        let container = try decoder.container(keyedBy: CodingKeys.self)

        widgetProperties = try WidgetProperties(from: decoder)
        sizes = try container.decode([String].self, forKey: .sizes)
        height = try container.decode(Float.self, forKey: .height)
    }
}
