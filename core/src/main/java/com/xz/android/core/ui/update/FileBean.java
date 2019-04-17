package com.xz.android.core.ui.update;

/**
 * 进度实体
 * Created by xiongz on 2018/1/19.
 */
public class FileBean {

    private long bytesRead;

    private long contentLength;

    private boolean isDone;

    public long getBytesRead() {
        return bytesRead;
    }

    public void setBytesRead(long bytesRead) {
        this.bytesRead = bytesRead;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
