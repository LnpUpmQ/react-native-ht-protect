//
//  NTESRiskErrorCode.h
//  对外
//
//  Created by Netease on 2022/4/27.
//  Copyright © 2022 Netease. All rights reserved.
//

#ifndef NTESRiskErrorCode_h
#define NTESRiskErrorCode_h

#define SET_ROLE_SUCCESS_CODE 0
#define SET_ROLE_NO_INIT_ERROR_CODE 201
#define SET_ROLE_PARAM_ERROR_CODE 203

#pragma mark - 错误码对应的原因

#define HTTP_RESPONSE_MSG     @"success"
#define INIT_SUCCESS_MSG      @"Success"
#define INIT_APPID_NULL_MSG  @"ProductId is null"
#define INIT_APPID_ERROR_MSG  @"Invalid ProductId!"
#define INIT_REPEAT_ERROR_MSG  @"Init Repeat!"
#define BUSINESSID_IVALID_MSG  @"businessId invalid"
#define RUN_ON_MAIN_THREAD_MSG  @"error. run on main thread"
#define NOT_INIT_ERROR_MSG  @"error. not init"
#define INIT_CONFIG_ERROR_MSG  @"Init failed with config!"
#define UPLOAD_ERROR_MSG  @"Upload failed!"
#define HTTP_RESPONSE_BUSINESS_ERROR_MSG @"Invalid Business!"
#define GET_TOKEN_ERROR_MSG @"get token error"
#define GET_TOKEN_CLOSED_MSG @"get token upload is closed."

#pragma mark - 错误码

#define INIT_SUCCESS_CODE  200
// appid错误导致初始化失败
#define INIT_APPID_ERROR_CODE  199
// 重复初始化
#define INIT_REPEAT_ERROR_CODE  109
// 还未初始化
#define GETTOKEN_NOT_INIT_ERROR_CODE 201
// 运行在主线程
#define GETTOKEN_ON_MAIN_THREAD_ERROR_CODE 202
// businessId不合法
#define GETTOKEN_BUSSINESSID_ERROR_CODE  203
// 其他错误
#define GETTOKEN_OTHER_ERROR_CODE 204

// 白盒V3版本错误码
// 能正常加密，错误码为正数
#define SC_OK 1           // 一切正常

// 不能加密，错误码为负数
#define SC_FILE_NOTEXIST -1 // apk包文件无文件
#define SC_FILE_IS_BROKEN -2   // apk包文件损坏
#define SC_PROTOCOL_VERSION_ERROR -3 // 协议版本不匹配
#define SC_PARAM_ERROR -4 // 参数错误
#define SC_DATA_TAMPERED -5 // 数据被篡改
#define SC_DATA_DECRYPT_ERROR -6 // 数据解密失败
#define SC_ALG_ERROR -7 // 算法不匹配,指定算法解密
#define SC_TIMEOUT -8 // 超时
#define SC_ENV -9 // 当前环境存在frida xposed unidbg
#define SC_DEVICE -10 // 设备不匹配

#define SC_BUFF_MALLOC_ERROR -98 // 这种情况一般不会出现
#define SC_INSIDE_ERROR -99 //内部错误，一般不会出现
#define SC_NOT_INIT -201 // 未初始化
#define SC_CLOSED -202 // 试用到期

#endif
