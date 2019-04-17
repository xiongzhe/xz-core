package com.xz.android.core.ui.camera;

import android.net.Uri;

import com.xz.android.core.activities.PermissionCheckerActivity;
import com.xz.android.core.util.file.FileUtil;

/**
 * 照相机调用类
 * Created by xiongz on 2018/1/15.
 */
public class XzCamera {

    /**
     * 创建裁剪的文件路径
     *
     * @return
     */
    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    /**
     * 开始拍照
     *
     * @param activity
     */
    public static void start(PermissionCheckerActivity activity) {
        new CameraHandler(activity).beginCameraDialog();
    }
}
