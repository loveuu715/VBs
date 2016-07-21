package vbs.vvi.com.bs.db.upgrade;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Wayne on 2016/7/21.
 */
public abstract class AbsDBUpgrade {
    public abstract void onUpgrade(Database db);
}
