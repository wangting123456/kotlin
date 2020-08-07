package com.example.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.greendao.gen.DaoMaster;
import com.example.greendao.gen.SingerDao;
import com.example.greendao.gen.StudentDao;

import org.greenrobot.greendao.database.Database;

/**
 * 自定义 DaoMaster.DevOpenHelper
 * 实现数据库升级功能
 */
public class MyOpenHelper extends DaoMaster.DevOpenHelper  {
    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //super.onUpgrade(db, oldVersion, newVersion);
        Log.d("MyOpenHelper", "onUpgrade: oldVersion = "+oldVersion);
        Log.d("MyOpenHelper", "onUpgrade: newVersion = "+newVersion);
        //修改版本号后，回调此方法
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {

            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                Log.d("MyOpenHelper", "onCreateAllTables");
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                Log.d("MyOpenHelper", "onDropAllTables");
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, SingerDao.class, StudentDao.class);
    }
}
