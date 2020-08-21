package com.example.ms.ms2;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileOutputStream;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler crashHandler = null;
    private Context mContext;

    private Thread.UncaughtExceptionHandler exceptionHandler;
    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        if (crashHandler == null) {
            synchronized (CrashHandler.class) {
                crashHandler = new CrashHandler();
            }
        }
        return crashHandler;
    }
    public void init(Context context) {
        mContext = context;
        exceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        if (handleException(e) && crashHandler != null) {
            crashHandler.uncaughtException(t, e);
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e1) {
                Log.i("xxx", "error: " + e1);
            }
        }

    }
    private boolean handleException(final Throwable e){
        if (e == null) {
            return false;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Log.i("xxx", "error: " + e.toString());
                Toast.makeText(mContext, "程序开小差了...", Toast.LENGTH_SHORT).show();
                /**
                 * 写入SD卡
                 */
                String sdFile = Environment.getExternalStorageDirectory().getAbsolutePath() + "/crash.txt";
                File file = new File(sdFile);
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    FileOutputStream outputStream = new FileOutputStream(file);
                    outputStream.write(e.toString().getBytes());
                    outputStream.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                Looper.loop();
            }
        }).start();
        return true;

    }
}
