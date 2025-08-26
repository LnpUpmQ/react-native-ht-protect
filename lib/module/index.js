"use strict";

import { NativeModules, Platform } from 'react-native';
const LINKING_ERROR = `The package 'react-native-ht-protect' doesn't seem to be linked. Make sure: \n\n` + Platform.select({
  ios: "- You have run 'pod install'\n",
  default: ''
}) + '- You rebuilt the app after installing the package\n' + '- You are not using Expo Go\n';

// @ts-expect-error
const isTurboModuleEnabled = global.__turboModuleProxy != null;
const HtProtectModule = isTurboModuleEnabled ? require('./NativeHtProtect').default : NativeModules.HtProtect;
const HtProtect = HtProtectModule ? HtProtectModule : new Proxy({}, {
  get() {
    throw new Error(LINKING_ERROR);
  }
});
export async function init(productId, config) {
  await HtProtect.init(productId, config);
}
export async function setRoleInfo(businessId, info) {
  if (businessId) {
    await HtProtect.setRoleInfo(businessId, Object.assign({
      roleName: '',
      roleAccount: '',
      roleServer: '',
      serverId: -1,
      gameJson: ''
    }, info));
  }
}
export async function getTokenAsync(businessId, timeout = 3000) {
  if (businessId) {
    return await HtProtect.getTokenAsync(businessId, timeout);
  }
}
//# sourceMappingURL=index.js.map