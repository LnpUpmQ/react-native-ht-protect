# react-native-ht-protect

Intelligent risk control for android and ios.

## Installation

```sh
npm install react-native-ht-protect
```

## for Android
### 过滤需要的 ABI
SDK 提供了 armeabi、armeabi-v7a、x86、arm64-v8a 四种 ABI 的支持，默认会导出这四种 ABI。

### 添加权限信息
SDK 不会申请任何权限，但是为了提升风控的效果，建议在 AndroidManifest.xml 文件中添加下列权限配置：
```xml
    <!--网络通信-->
    <uses-permission android:name="android.permission.INTERNET"/>
      <!--获取设备信息-->
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
      <!--获取MAC地址-->
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
      <!--获取网络状态-->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
      <!--若targetSdkVersion>=30，则需要增加以下权限，否则会影响SDK功能-->
    <queries>
      <intent>
        <action android:name="android.intent.action.MAIN"/>
      </intent>
    </queries>
```

### 添加 ProGuard 配置
若使用 ProGuard 进行混淆，需要将 SDK 使用的类排除掉。若使用 Android Studio 开发，则在 proguard-rules.pro 文件中添加如下信息：
```
 -keep class com.netease.htprotect.**{*;}
 -keep class com.netease.mobsec.**{*;}
```

## for iOS


## Usage


```js
import { init } from 'react-native-ht-protect';

// ...

// ...
```


## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
