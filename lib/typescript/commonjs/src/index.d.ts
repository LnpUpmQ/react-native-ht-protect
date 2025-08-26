import type { TConfig, TRoleInfo } from './NativeHtProtect';
export declare function init(productId: string, config: TConfig): Promise<void>;
export declare function setRoleInfo(businessId: string, info: TRoleInfo): Promise<void>;
export declare function getTokenAsync(businessId: string, timeout?: number): Promise<string | void>;
//# sourceMappingURL=index.d.ts.map