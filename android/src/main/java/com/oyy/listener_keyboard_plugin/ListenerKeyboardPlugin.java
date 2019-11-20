package com.oyy.listener_keyboard_plugin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** ListenerKeyboardPlugin */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class ListenerKeyboardPlugin implements  MethodCallHandler, ViewTreeObserver.OnGlobalLayoutListener,
        Application.ActivityLifecycleCallbacks {


  View mainLayout;

  boolean isShow; // show or hide keyboard

  Registrar registrar;

  MethodCall call;

  Result result;

  ListenerKeyboardPlugin(Registrar registrar) {
    this.registrar = registrar;
  }

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {

    ListenerKeyboardPlugin listenerPlugin =  new ListenerKeyboardPlugin(registrar);
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "listener_keyboard_plugin");
    channel.setMethodCallHandler(listenerPlugin);

  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    this.result = result;
    this.call = call;

    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onActivityCreated(Activity activity, Bundle bundle) {

    mainLayout = ((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0);
    mainLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);

  }

  @Override
  public void onActivityStarted(Activity activity) {

  }

  @Override
  public void onActivityResumed(Activity activity) {

  }

  @Override
  public void onActivityPaused(Activity activity) {

  }

  @Override
  public void onActivityStopped(Activity activity) {

  }

  @Override
  public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

  }

  @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
  @Override
  public void onActivityDestroyed(Activity activity) {

    if (mainLayout != null) {

      mainLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
      mainLayout = null;
    }

  }

  @Override
  public void onGlobalLayout() {

    Rect outRect = new Rect();
    mainLayout.getWindowVisibleDisplayFrame(outRect);

    isShow = outRect.height() / mainLayout.getRootView().getHeight() < 0.7 ? true : false;

    // start
    if (call.method.equals("getShowKeyboard")) {
      this.result.success(isShow);
    }

  }
}
