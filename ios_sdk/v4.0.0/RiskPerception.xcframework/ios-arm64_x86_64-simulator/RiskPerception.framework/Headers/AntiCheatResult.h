//
//  AntiCheatResult.h
//  RiskPerception
//
//  Created by Netease on 2023/1/10.
//  Copyright © 2023 Netease. All rights reserved.
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN
__attribute__((visibility("default")))
@interface AntiCheatResult : NSObject

@property (nonatomic, copy) NSString *token;
@property (nonatomic, copy) NSString *businessId;
@property (nonatomic, assign) int code;
@property (nonatomic, copy) NSString *codeStr;

@end

NS_ASSUME_NONNULL_END
