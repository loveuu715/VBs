package vbs.vvi.com.bs.db.upgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import vbs.vvi.com.bs.db.DaoMaster;

/**
 * Created by Wayne on 2016/7/21.
 */
public class UpgradeHelper extends DaoMaster.OpenHelper {

    public UpgradeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        for (int i = oldVersion; i < newVersion; i++) {
            try {
                AbsDBUpgrade migratorHelper = (AbsDBUpgrade) Class.forName("vbs.vvi.com.bs.db.DbUpgradeHelper" + i).newInstance();
                if (migratorHelper != null) {
                    //更新数据库
                    migratorHelper.onUpgrade(db);
                }
            } catch (ClassNotFoundException | ClassCastException | IllegalAccessException | InstantiationException e) {
                Log.e("hate", "Could not migrate from schema from schema: " + i + " to " + i++);
                break;
            }
        }
    }
}
