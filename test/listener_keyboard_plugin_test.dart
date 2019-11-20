import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:listener_keyboard_plugin/listener_keyboard_plugin.dart';

void main() {
  const MethodChannel channel = MethodChannel('listener_keyboard_plugin');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await ListenerKeyboardPlugin.platformVersion, '42');
  });
}
