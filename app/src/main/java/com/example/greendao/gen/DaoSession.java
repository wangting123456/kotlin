package com.example.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.greendao.entity.Singer;
import com.example.greendao.entity.Student;

import com.example.greendao.gen.SingerDao;
import com.example.greendao.gen.StudentDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig singerDaoConfig;
    private final DaoConfig studentDaoConfig;

    private final SingerDao singerDao;
    private final StudentDao studentDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        singerDaoConfig = daoConfigMap.get(SingerDao.class).clone();
        singerDaoConfig.initIdentityScope(type);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        singerDao = new SingerDao(singerDaoConfig, this);
        studentDao = new StudentDao(studentDaoConfig, this);

        registerDao(Singer.class, singerDao);
        registerDao(Student.class, studentDao);
    }
    
    public void clear() {
        singerDaoConfig.clearIdentityScope();
        studentDaoConfig.clearIdentityScope();
    }

    public SingerDao getSingerDao() {
        return singerDao;
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

}
