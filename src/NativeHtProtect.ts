import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface TConfig {
  gameKey: string | undefined;
  serverType: number | undefined;
  channel: string | undefined;
  extraData: { [k: string]: string } | undefined;
}

export interface TRoleInfo {
  roleId: string | undefined;
  roleName: string | undefined;
  roleAccount: string;
  roleServer: string | undefined;
  serverId: number | undefined;
  gameJson: string | undefined;
}

export enum RequestCmdID {
  //初始化配置内容，防止初始化失败情况下，客户可以通过服务端请求初始化配置并传递给SDK
  Cmd_SetConfigData = 16,
  //客户服务端将check结果传递给SDK，便于执行后续动作
  Cmd_SetResponseData = 17,
}

export interface Spec extends TurboModule {
  init(
    productId: string,
    config: TConfig
  ): Promise<{ code: number; message: string }>;

  setRoleInfo(businessId: string, info: TRoleInfo): Promise<void>;

  getTokenAsync(businessId: string, timeout: number): Promise<string>;

  ioctl(request: RequestCmdID, data?: string): Promise<void>;
}

export default TurboModuleRegistry.getEnforcing<Spec>('HtProtect');
