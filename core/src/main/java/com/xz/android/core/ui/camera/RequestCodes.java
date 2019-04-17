package com.xz.android.core.ui.camera;

import com.yalantis.ucrop.UCrop;

/**
 * 请求码存储
 * Created by xiongz on 2018/1/15.
 */
public class RequestCodes {
    //拍照
    public static final int TAKE_PHOTO = 4;
    //相册
    public static final int PICK_PHOTO = 5;
    //裁剪
    public static final int CROP_PHOTO = UCrop.REQUEST_CROP;
    //裁剪出错
    public static final int CROP_ERROR = UCrop.RESULT_ERROR;
}
