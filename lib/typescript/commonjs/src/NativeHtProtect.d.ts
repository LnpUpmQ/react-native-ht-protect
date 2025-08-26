import type { TurboModule } from 'react-native';
export interface TConfig {
    gameKey: string | undefined;
    serverType: number | undefined;
    channel: string | undefined;
    extraData: Record<string, string> | undefined;
}
export interface TRoleInfo {
    roleId: string | undefined;
    roleName: string | undefined;
    roleAccount: string;
    roleServer: string | undefined;
    serverId: number | undefined;
    gameJson: string | undefined;
}
export interface Spec extends TurboModule {
    init(productId: string, config: TConfig): Promise<void>;
    setRoleInfo(businessId: string, info: TRoleInfo): Promise<void>;
    getTokenAsync(businessId: string, timeout: number): Promise<string>;
}
declare const _default: Spec;
export default _default;
//# sourceMappingURL=NativeHtProtect.d.ts.map