package com.xz.android.core.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.xz.android.core.R;
import com.xz.android.core.permissions.PermissionCallBack;
import com.xz.android.core.ui.webview.X5ProgressWebView;
import com.tencent.smtt.sdk.CookieManager;
import com.tencent.smtt.sdk.DownloadListener;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

/**
 * @描述 webview的Activity
 * @创建人 winkey
 * @创建日期 2018/11/12
 */
public abstract class XzWebActivity extends XzActivity {

    private X5ProgressWebView mWebView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        super.onCreate(savedInstanceState);
    }

    /**
     * 设置web url
     *
     * @param activity
     * @param url
     */
    protected void setLoadUrl(final Activity activity, String url) {
        mWebView = activity.findViewById(R.id.x5_web_view);
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(final String url, String userAgent,
                                        final String contentDisposition,
                                        String mimetype, long contentLength) {
                startStorage(new PermissionCallBack() {
                    @Override
                    public void handle() {
                        download(url, contentDisposition, activity);
                    }
                });
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl(url);
    }

    /**
     * 下载
     */
    private void download(String url, String contentDisposition, Activity activity) {
        final DownloadManager downloadManager = ((DownloadManager) getSystemService(DOWNLOAD_SERVICE));
        final DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setVisibleInDownloadsUi(true);
        //传cookie
        String CookieStr = CookieManager.getInstance().getCookie(url);
        request.addRequestHeader("Cookie", CookieStr);
        //contentDisposition本身带有att...一串字符,中文和非中文还不一样
        String fileName;
        if (contentDisposition.contains("filename*=UTF-8")) {
            fileName = contentDisposition.substring(contentDisposition.indexOf("'") + 2, contentDisposition.length());
        } else {
            fileName = contentDisposition.substring(contentDisposition.indexOf("=") + 1, contentDisposition.length()).replace("\"", "");
        }
        if (fileName.equals("")) {
            fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        new AlertDialog.Builder(activity)
                .setTitle("下载附件")
                .setMessage("立刻下载附件\"" + fileName + "\"？")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        downloadManager.enqueue(request);
                        ToastUtils.showLong("文件下载中...");
                        dialogInterface.dismiss();
                    }
                }).create().show();
    }
}
