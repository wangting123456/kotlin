package com.example.greendao;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.greendao.entity.Singer;
import com.example.greendao.gen.SingerDao;
import com.example.kotlin.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava1);
        SingerDao singerDao = DaoSessionManager.getInstace().getDaoSession(getApplicationContext()).getSingerDao();
        //创建对象
        Singer singer = new Singer();
        singer.setName("zh");
        //增加
        singerDao.insert(singer);
        singer.setName("ch");
        //修改
        singerDao.update(singer);
        //查询
        //Singer singer_query = singerDao.queryBuilder().where(SingerDao.Properties.Id.eq(111)).list().get(0);
        //删除
        singerDao.delete(singer);
    }
}
