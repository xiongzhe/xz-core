package com.xz.android.core.fragments;

import android.Manifest;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.blankj.utilcode.util.ToastUtils;
import com.xz.android.core.permissions.PermissionCallBack;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Android 6.0权限基类
 * Created by xiongz on 2017/12/10.
 */
@RuntimePermissions
public abstract class PermissionCheckerFragment extends BaseFragment {

    /**
     * 调用获取相机权限
     */
    public void startCamera(PermissionCallBack callBack) {
        PermissionCheckerFragmentPermissionsDispatcher.cameraWithPermissionCheck(this, callBack);
    }

    /**
     * 调用获取读取文件权限
     */
    public void startStorage(PermissionCallBack callBack) {
        PermissionCheckerFragmentPermissionsDispatcher.storageWithPermissionCheck(this, callBack);
    }

    /**
     * 调用获取打电话权限
     */
    public void startCall(PermissionCallBack callBack) {
        PermissionCheckerFragmentPermissionsDispatcher.callWithPermissionCheck(this, callBack);
    }

    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void camera(PermissionCallBack callBack) {
        callBack.handle();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void cameraRationale(final PermissionRequest request) {
        new AlertDialog.Builder(getProxyActivity())
                .setMessage("使用该功能需要获取相机权限，下一步将继续请求权限")
                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).show();
    }

    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void cameraDenied() {
        ToastUtils.showLong("已拒绝获取相机权限");
    }

    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void cameraNeverAsk() {
        ToastUtils.showLong("已拒绝获取相机权限，并不再询问");
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void storage(PermissionCallBack callBack) {
        callBack.handle();
    }

    @OnShowRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void storageRationale(final PermissionRequest request) {
        new AlertDialog.Builder(getActivity())
                .setMessage("使用该功能需要获取读写文件权限，下一步将继续请求权限")
                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).show();
    }

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void storageDenied() {
        ToastUtils.showLong("已拒绝获取读写文件权限");
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void storageNeverAsk() {
        ToastUtils.showLong("已拒绝获取读写文件权限，并不再询问");
    }

    @NeedsPermission(Manifest.permission.CALL_PHONE)
    void call(PermissionCallBack callBack) {
        callBack.handle();
    }

    @OnShowRationale(Manifest.permission.CALL_PHONE)
    void callRationale(final PermissionRequest request) {
        new AlertDialog.Builder(getProxyActivity())
                .setMessage("使用该功能需要获取打电话权限，下一步将继续请求权限")
                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).show();
    }

    @OnPermissionDenied(Manifest.permission.CALL_PHONE)
    void callDenied() {
        ToastUtils.showLong("已拒绝获取打电话权限");
    }

    @OnNeverAskAgain(Manifest.permission.CALL_PHONE)
    void callNeverAsk() {
        ToastUtils.showLong("已拒绝获取打电话权限，并不再询问");
    }


    /**
     * 调用定位权限
     */
    public void startLocation(PermissionCallBack callBack) {
        PermissionCheckerFragmentPermissionsDispatcher.locationWithPermissionCheck(this, callBack);
    }

    @NeedsPermission({
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE})
    void location(PermissionCallBack callBack) {
        callBack.handle();
    }

    @OnShowRationale({
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE})
    void locationRationale(final PermissionRequest request) {
        new AlertDialog.Builder(getProxyActivity())
                .setMessage("使用该功能需要获取定位权限，下一步将继续请求权限")
                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).show();
    }

    @OnPermissionDenied({
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE})
    void locationDenied() {
        ToastUtils.showLong("已拒绝获取定位权限");
    }

    @OnNeverAskAgain({
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE})
    void locationNeverAsk() {
        ToastUtils.showLong("已拒绝获取定位权限，并不再询问");
    }

    /********** 手机信息 *********/

    // 调用手机信息相关权限
    public void startPhone(PermissionCallBack callBack) {
        PermissionCheckerFragmentPermissionsDispatcher.phoneWithPermissionCheck(this, callBack);
    }

    @NeedsPermission({Manifest.permission.READ_PHONE_STATE})
    void phone(PermissionCallBack callBack) {
        callBack.handle();
    }

    @OnShowRationale({Manifest.permission.READ_PHONE_STATE})
    void phoneRationale(final PermissionRequest request) {
        new AlertDialog.Builder(getProxyActivity())
                .setMessage("使用该功能需要获取手机信息相关权限，下一步将继续请求权限")
                .setPositiveButton("下一步", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).show();
    }

    @OnPermissionDenied({Manifest.permission.READ_PHONE_STATE})
    void phoneDenied() {
        ToastUtils.showLong("已拒绝获取手机信息相关权限");
    }

    @OnNeverAskAgain({Manifest.permission.READ_PHONE_STATE})
    void phoneNeverAsk() {
        ToastUtils.showLong("已拒绝获取手机信息相关权限，并不再询问");
    }
}
