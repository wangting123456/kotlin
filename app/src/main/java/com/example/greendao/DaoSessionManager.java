package com.example.greendao;

import android.content.Context;

import com.example.greendao.gen.DaoMaster;
import com.example.greendao.gen.DaoSession;

public class DaoSessionManager {
    private final String DB_NAME = "android.db";
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private DaoSessionManager() {
    }
    public static DaoSessionManager mInstance = new DaoSessionManager();
    public static DaoSessionManager getInstace() {
        return mInstance;
    }
/*    public DaoMaster getDaoMaster(Context context){
        DaoMaster.DevOpenHelper mHelper = new DaoMaster.DevOpenHelper(context,DB_NAME,null);
        daoMaster = new DaoMaster( mHelper.getWritableDatabase());
        return daoMaster;
    }*/
public DaoMaster getDaoMaster(Context context){
    MyOpenHelper mHelper = new MyOpenHelper(context,DB_NAME,null);
    daoMaster = new DaoMaster(mHelper.getWritableDatabase());
    return daoMaster;
}
    public DaoSession getDaoSession(Context context){
        if(daoSession == null){
            if(daoMaster == null){
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }
}
