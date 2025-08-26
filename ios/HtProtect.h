
#ifdef RCT_NEW_ARCH_ENABLED
#import "RNHtProtectSpec.h"

@interface HtProtect : NSObject <NativeHtProtectSpec>
#else
#import <React/RCTBridgeModule.h>

@interface HtProtect : NSObject <RCTBridgeModule>
#endif

@end
