#import "ListenerKeyboardPlugin.h"
#import <listener_keyboard_plugin/listener_keyboard_plugin-Swift.h>

@implementation ListenerKeyboardPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftListenerKeyboardPlugin registerWithRegistrar:registrar];
}
@end
