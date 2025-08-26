#import "HtProtect.h"
#import <RiskPerception/NTESRiskUniConfiguration.h>
#import <RiskPerception/NTESRiskUniPerception.h>

@implementation HtProtect
RCT_EXPORT_MODULE()


RCT_EXPORT_METHOD(init:(NSString *)productId
                  params:(NSDictionary *)params
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
{
  try {
    if ([params objectForKey:@"serverType"]) {
      // 设置数据上报的服务器归属地，应用/游戏根据自身发行地区来控制
      [NTESRiskUniConfiguration setServerType:[params[@"serverType"] integerValue]];
    }
    if ([params objectForKey:@"channel"]) {
      // 设置渠道信息
      [NTESRiskUniConfiguration setChannel:params[@"channel"]];
    }
    if ([params objectForKey:@"extraData"]) {
      // 设置额外数据
      NSDictionary *map = [params objectForKey:@"extraData"];
      NSArray *aryKey = [map allKeys];
      for (NSString *key in aryKey) {
        NSString *value = [map objectForKey:key];
        [NTESRiskUniConfiguration setExtraData:key forValue:value];
      }
    }
    //调用sdk初始化接口
    [[NTESRiskUniPerception fomentBevelDeadengo] init:productId callback:^(int code, NSString * _Nonnull msg, NSString * _Nonnull content) {
      // code返回200说明初始化成功
      if (code == 200) {
        resolve(nil);
      } else {
        NSError *error = [NSError errorWithDomain:@"" code:code  userInfo:nil];
        reject([@(code) stringValue], [[NSString alloc] initWithFormat:@"[HTProtect]: init failed with code %@(%@).", [@(code) stringValue], msg], error);
      }
    }];
  } catch (NSError *error) {
    reject(@"-1", @"[HTProtect]: init failed.", error);
  }
}

RCT_EXPORT_METHOD(setRoleInfo:(NSString *)businessId
                  info:(NSDictionary *)info
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
{
  try {
    int resultCode = [[NTESRiskUniPerception fomentBevelDeadengo] setRoleInfo: businessId
                                                                       roleId:info[@"roleId"]
                                                                     roleName:info[@"roleName"]
                                                                  roleAccount:info[@"roleAccount"]
                                                                   roleServer:info[@"roleServer"]
                                                                     serverId:[info[@"serverId"] intValue]
                                                                     gameJson:info[@"gameJson"]];

    /*
     0  成功
     -201  未初始化
     -203  businessId 不合法
     */
    if (resultCode == 0) {
      resolve(nil);
    } else {
      NSError *error = [NSError errorWithDomain:@"" code:resultCode  userInfo:nil];
      reject([@(resultCode) stringValue], [[NSString alloc] initWithFormat:@"[HTProtect]: set role info failed with code %@.", [@(resultCode) stringValue]], error);
    }

  } catch (NSError *error) {
    reject(@"-1", @"[HTProtect]: set role info failed.", error);
  }
}


RCT_EXPORT_METHOD(getTokenAsync:(NSString *)businessId
                  timeout:(NSInteger *)timeout
                  resolve:(RCTPromiseResolveBlock)resolve
                  reject:(RCTPromiseRejectBlock)reject)
{
  try {
    [[NTESRiskUniPerception fomentBevelDeadengo] getTokenAsync:businessId withTimeout:3000 completeHandler:^(AntiCheatResult * _Nonnull result) {
      if (result.code == 200) {
        // 调用成功，获取token
        resolve(result.token);
      } else {
        NSError *error = [NSError errorWithDomain:@"" code:result.code userInfo:nil];
        reject([@(result.code) stringValue], [[NSString alloc] initWithFormat:@"[HTProtect]: get token failed with code %@(%@).", [@(result.code) stringValue], result.codeStr], error);
      }
    }];
  }catch (NSError *error) {
    reject(@"-1", @"[HTProtect]: get token failed.", error);
  }
}

// Don't compile this code when we build for the old architecture.
#ifdef RCT_NEW_ARCH_ENABLED
- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
(const facebook::react::ObjCTurboModule::InitParams &)params
{
  return std::make_shared<facebook::react::NativeHtProtectSpecJSI>(params);
}
#endif

@end
