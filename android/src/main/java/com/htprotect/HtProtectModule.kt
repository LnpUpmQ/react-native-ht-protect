package com.htprotect

import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.netease.htprotect.HTProtect
import com.netease.htprotect.HTProtectConfig
import com.netease.htprotect.callback.GetTokenCallback
import com.netease.htprotect.callback.HTPCallback
import com.netease.htprotect.result.AntiCheatResult


class HtProtectModule internal constructor(context: ReactApplicationContext) :
  HtProtectSpec(context) {

  private var reactApplicationContext = context;

  override fun getName(): String {
    return NAME
  }


  @ReactMethod
  override fun init(productId: String, params: ReadableMap, promise: Promise) {
    try {
      val config = HTProtectConfig()
      if (params.hasKey("gameKey")) {
        // 设置gamekey，具体值请联系易盾客服
        config.setGameKey(params.getString("gameKey"))
      }
      if (params.hasKey("serverType")) {
        // 设置数据上报的服务器归属地，应用/游戏根据自身发行地区来控制
        config.setServerType(params.getInt("serverType"))
      }
      if (params.hasKey("channel")) {
        // 设置渠道信息
        config.setChannel(params.getString("channel"))
      }
      if (params.hasKey("extraData")) {
        // 设置额外数据
        val map = params.getMap("extraData");
        val iterator = map?.entryIterator
        if (iterator != null) {
          while (iterator.hasNext()) {
            val item = iterator.next();
            config.setExtraData(item.key, item.value.toString())
          }
        }
      }


      val callback = HTPCallback { paramInt, paramString ->
        Log.d("HTProtect", "code is:$paramInt String is:$paramString")
        // paramInt返回200说明初始化成功
        if (paramInt == 200) {
          promise.resolve(null)
        } else {
          promise.reject(
            paramInt.toString(),
            Throwable("[HTProtect]: init failed with code ${paramInt}(${paramString}).")
          );
        }
      }

      //调用sdk初始化接口init函数
      HTProtect.init(reactApplicationContext.applicationContext, productId, callback, config)

    } catch (e: Error) {
      promise.reject("-1", Throwable("[HTProtect]: init failed."));
    }
  }


  @ReactMethod
  override fun setRoleInfo(businessId: String, info: ReadableMap, promise: Promise) {
    try {
      val resultCode = HTProtect.setRoleInfo(
        businessId,
        info.getString("roleId"),
        info.getString("roleName"),
        info.getString("roleAccount"),
        info.getString("roleServer"),
        info.getInt("serverId"),
        info.getString("gameJson"),
      );
      /*
        0	成功
        -201	未初始化
        -203	businessId 不合法
      */
      if (resultCode == 0) {
        promise.resolve(null)
      } else {
        promise.reject(
          resultCode.toString(),
          Throwable("[HTProtect]: set role info failed with code ${resultCode}.")
        );
      }

    } catch (err: Error) {
      promise.reject("-1", Throwable("[HTProtect]: set role info failed."));
    }
  }


  @ReactMethod
  override fun getTokenAsync(businessId: String, timeout: Int, promise: Promise) {
    try {
      val myGetTokenCallback = GetTokenCallback { antiCheatResult ->
        if (antiCheatResult.code == AntiCheatResult.OK) {
          // 调用成功，获取token
          promise.resolve(antiCheatResult.token)
        } else {
          promise.reject(
            antiCheatResult.code.toString(),
            Throwable("[HTProtect]: get token failed with code ${antiCheatResult.code}(${antiCheatResult.codeStr}).")
          );
        }
      }

      HTProtect.getTokenAsync(timeout, businessId, myGetTokenCallback)
    } catch (e: Error) {
      promise.reject("-1", Throwable("[HTProtect]: get token failed."));
    }
  }


  companion object {
    const val NAME = "HtProtect"
  }
}
