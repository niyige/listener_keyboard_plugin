import 'dart:async';

import 'package:flutter/services.dart';

class ListenerKeyboardPlugin {
  static const MethodChannel _channel =
      const MethodChannel('listener_keyboard_plugin');

  //默认生成的
  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }


  static Future<bool> get showKeyboard async {
    final bool show = await _channel.invokeMethod("getShowKeyboard");

    return show;

  }
}
