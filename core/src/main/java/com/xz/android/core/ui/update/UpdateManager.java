package com.xz.android.core.ui.update;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;

import com.blankj.utilcode.util.ToastUtils;
import com.xz.android.core.app.Xz;
import com.xz.android.core.net.rx.RxNetClient;
import com.xz.android.core.util.file.FileUtil;

import java.io.File;
import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import okhttp3.ResponseBody;

/**
 * 更新app版本管理类
 * Created by xiongz on 2018/1/18.
 */
public class UpdateManager {

    //进度对话框
    private static ProgressDialog dialog;

    private static final class Holder {
        private static final UpdateManager INSTANCE = new UpdateManager();
    }

    public static UpdateManager getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 下载apk文件
     */
    public void download(final Activity context, String url) {
        showDownloadDialog(context);
        final String fileName = "mljia.apk";
        final String fileStoreDir = FileUtil.SDCARD_ABSOLUTE_DIR + "/mljia";
        final FileDownLoadObserver fileDownLoadObserver = new FileDownLoadObserver<File>() {
            @Override
            public void onDownLoadSuccess(File file) {
                dialog.dismiss();
                installApk(context, file);
            }

            @Override
            public void onDownLoadFail(Throwable throwable) {
                dialog.dismiss();
                ToastUtils.showShort("下载失败");
            }

            @Override
            public void onProgress(long progress, long total) {
                double progressD = FileUtil.getFormatMBSize(progress);
                double totalD = FileUtil.getFormatMBSize(total);
                dialog.setProgressNumberFormat(String.format("%.2fMB/%.2fMB", (float) progressD, (float) totalD));
                dialog.setProgress((int) progressD);
                dialog.setMax((int) totalD);
            }
        };

        url = "http://dl.mljia.cn/app/mljia_shop_v2.5.2.apk?";//测试数据
        final HashMap<String, Object> params = new HashMap<>();
        RxNetClient.builder()
                .url(url)
                .params(params)
                .build()
                .download()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(@NonNull ResponseBody responseBody) throws Exception {
                        return fileDownLoadObserver.saveFile(responseBody, fileStoreDir, fileName);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(fileDownLoadObserver);
    }

    /**
     * 显示下载弹窗
     */
    private void showDownloadDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setTitle("版本更新");
        dialog.setMessage("正在下载，请稍后...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setCancelable(false);
        dialog.show();
    }

    /**
     * 安装
     */
    public void installApk(Activity context, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判读版本是否在7.0以上
            Uri apkUri = FileProvider.getUriForFile(Xz.getApplicationContext(), "cn.mljia.shop.fileprovider", file);
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);//添加这一句表示对目标应用临时授权该Uri所代表的文件
            install.setDataAndType(apkUri, "application/vnd.android.package-archive");
            context.startActivity(install);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse("file://" + file.getAbsolutePath()), "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}