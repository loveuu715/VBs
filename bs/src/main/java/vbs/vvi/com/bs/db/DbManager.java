package vbs.vvi.com.bs.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Wayne on 2016/7/21.
 */
public class DBManager {
    private final static String dbName = "DBUSER_BEAN";
    private static volatile DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入一条记录
     *
     * @param user
     */
    public synchronized void insertUser(DBUserBean user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DBUserBeanDao userDao = daoSession.getDBUserBeanDao();
        userDao.insertOrReplace(user);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public synchronized void insertUserList(List<DBUserBean> users) {
        if (users == null || users.isEmpty())
            return;
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DBUserBeanDao userDao = daoSession.getDBUserBeanDao();
        userDao.insertOrReplaceInTx(users);
    }

    /**
     * 删除一条记录
     *
     * @param user
     */
    public synchronized void deleteUser(DBUserBean user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DBUserBeanDao userDao = daoSession.getDBUserBeanDao();
        userDao.delete(user);
    }

    /**
     * 更新一条记录
     *
     * @param user
     */
    public synchronized void updateUser(DBUserBean user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DBUserBeanDao userDao = daoSession.getDBUserBeanDao();
        userDao.update(user);
    }

    /**
     * 查询所有用户列表
     */
    public List<DBUserBean> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DBUserBeanDao userDao = daoSession.getDBUserBeanDao();
        QueryBuilder<DBUserBean> qb = userDao.queryBuilder();
        List<DBUserBean> list = qb.list();
        return list;
    }

    /**
     * 条件查询用户列表
     */
    public List<DBUserBean> queryUserList(long userKey) {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DBUserBeanDao userDao = daoSession.getDBUserBeanDao();
        QueryBuilder<DBUserBean> qb = userDao.queryBuilder();
        qb.where(DBUserBeanDao.Properties.UserKey.gt(userKey));
        List<DBUserBean> list = qb.list();
        return list;
    }

    public DBUserBean queryUser(String userKey) {
        DBUserBean userBean = null;
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        DBUserBeanDao userDao = daoSession.getDBUserBeanDao();
        QueryBuilder<DBUserBean> qb = userDao.queryBuilder();
        userBean = qb.where(DBUserBeanDao.Properties.UserKey.gt(userKey)).unique();
        return userBean;
    }
}
