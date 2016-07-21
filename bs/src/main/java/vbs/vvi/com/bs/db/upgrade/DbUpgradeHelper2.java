package vbs.vvi.com.bs.db.upgrade;

import org.greenrobot.greendao.database.Database;

/**
 * 例如从1升到2
 * Created by Wayne on 2016/7/21.
 */
public class DbUpgradeHelper2 extends AbsDBUpgrade {
    @Override
    public void onUpgrade(Database db) {
        //新建一个表和旧表一样, 作为临时表
        db.execSQL("CREATE TABLE " + "\"DBUSER_BEAN_COPY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: userId
                "\"USER_KEY\" INTEGER NOT NULL ," + // 1: userKey
                "\"STATUS\" INTEGER NOT NULL ," + // 2: status
                "\"PHONE\" TEXT," + // 3: phone
                "\"BIRTHDAY\" INTEGER NOT NULL ," + // 4: birthday
                "\"TIP_SETTING\" INTEGER NOT NULL ," + // 5: tipSetting
                "\"GENDER\" INTEGER NOT NULL ," + // 6: gender
                "\"IMPORTANT\" INTEGER NOT NULL ," + // 7: important
                "\"REMARK\" TEXT," + // 8: remark
                "\"CREATE_TIME\" INTEGER NOT NULL ," + // 9: createTime
                "\"ADDRESS\" TEXT," + // 10: address
                "\"AVATAR_PATH\" TEXT," + // 11: avatarPath
                "\"RELATIONSHIP\" INTEGER NOT NULL ," + // 12: relationship
                "\"GROUP_INFO\" INTEGER NOT NULL ," + // 13: groupInfo
                "\"GROUP_NAME\" TEXT);"); // 14: groupName

        //拷贝旧表中的数据到临时表中
        db.execSQL("INSERT INTO DBUSER_BEAN_COPY (_id, USER_KEY, STATUS, PHONE, BIRTHDAY,  TIP_SETTING, GENDER, IMPORTANT, " +
                "REMARK, CREATE_TIME, ADDRESS, AVATAR_PATH, RELATIONSHIP, GROUP_INFO, GROUP_NAME)" +
                "   SELECT _id, USER_KEY, STATUS, PHONE, BIRTHDAY,  TIP_SETTING, GENDER, IMPORTANT, " +
                "REMARK, CREATE_TIME, ADDRESS, AVATAR_PATH, RELATIONSHIP, GROUP_INFO, GROUP_NAME FROM DBUSER_BEAN;");

        //删除旧表
        db.execSQL("DROP TABLE DBUSER_BEAN");

        //对临时表进行重命名
        db.execSQL("ALTER TABLE DBUSER_BEAN_COPY RENAME TO DBUSER_BEAN");


        //TODO 以下两个待处理
        db.execSQL("CREATE INDEX " + "IDX_post_USER_ID ON post" +
                " (USER_ID);");
        //Example sql statement
        db.execSQL("ALTER TABLE user ADD COLUMN USERNAME TEXT");
    }
}
