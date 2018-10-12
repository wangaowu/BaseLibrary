package com.unistrong.baselibs.utils;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;

/**
 * 简单指纹帮助类
 */
public class FingerGestureHelp {
    private static final String TAG = "FingerGestureHelp";

    public interface OnGestureResult {
        void success();

        void failure();

        void error();
    }

    private OnGestureResult listener;

    private static FingerGestureHelp instance;

    public static FingerGestureHelp getInstance() {
        return instance == null ? new FingerGestureHelp() : instance;
    }

    private Context context;

    public void initFinger(Activity context, OnGestureResult listener) {
        this.context = context;
        this.listener = listener;
        //测试指纹
        FingerprintManager fingerprintManager;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = (FingerprintManager) context.getSystemService(Context.FINGERPRINT_SERVICE);
            boolean hardwareDetected = fingerprintManager.isHardwareDetected();
            boolean hasSetFingers = fingerprintManager.hasEnrolledFingerprints();
            Log.e(TAG, "支持指纹吗?" + hardwareDetected + ",有设置指纹吗？" + hasSetFingers);

            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
            boolean keyguardSecure = keyguardManager.isKeyguardSecure();
            Log.e(TAG, "附加锁屏密码?" + keyguardSecure);

            if (keyguardSecure && hardwareDetected && hasSetFingers && fingerprintManager != null) {
                CancellationSignal cancellationSignal = new CancellationSignal();
                cancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener() {
                    @Override
                    public void onCancel() {
                        showToast("指纹取消!");
                    }
                });
                fingerprintManager.authenticate(null,
                        cancellationSignal,
                        0,
                        new CallBack(),
                        null);
                Toast.makeText(context, "请按下指纹..", Toast.LENGTH_LONG).show();
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    class CallBack extends FingerprintManager.AuthenticationCallback {
        @Override
        public void onAuthenticationError(int errorCode, CharSequence errString) {
            super.onAuthenticationError(errorCode, errString);
            showToast("验证错误:" + errString);
            if (listener != null) {
                listener.error();
            }
        }

        @Override
        public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
            super.onAuthenticationSucceeded(result);
            showToast("验证成功:" + result.toString());
            if (listener != null) {
                listener.success();
            }
        }

        @Override
        public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
            super.onAuthenticationHelp(helpCode, helpString);
            showToast("onAuthenticationHelp: " + "移动太快或者其他原因");
        }

        @Override
        public void onAuthenticationFailed() {
            super.onAuthenticationFailed();
            if (listener != null) {
                listener.failure();
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
