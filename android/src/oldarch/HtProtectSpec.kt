package com.htprotect

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReadableMap

abstract class HtProtectSpec internal constructor(context: ReactApplicationContext) :
  ReactContextBaseJavaModule(context) {

  abstract fun init(productId: String, params: ReadableMap, promise: Promise)

  abstract fun setRoleInfo(businessId: String, info: ReadableMap, promise: Promise)

  abstract fun getTokenAsync(businessId: String, timeout: Int, promise: Promise)


}
