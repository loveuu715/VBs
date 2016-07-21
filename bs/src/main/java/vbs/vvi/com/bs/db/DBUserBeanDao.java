package vbs.vvi.com.bs.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DBUSER_BEAN".
*/
public class DBUserBeanDao extends AbstractDao<DBUserBean, Long> {

    public static final String TABLENAME = "DBUSER_BEAN";

    /**
     * Properties of entity DBUserBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property UserId = new Property(0, long.class, "userId", true, "_id");
        public final static Property UserKey = new Property(1, long.class, "userKey", false, "USER_KEY");
        public final static Property Status = new Property(2, int.class, "status", false, "STATUS");
        public final static Property Phone = new Property(3, String.class, "phone", false, "PHONE");
        public final static Property Birthday = new Property(4, long.class, "birthday", false, "BIRTHDAY");
        public final static Property TipSetting = new Property(5, int.class, "tipSetting", false, "TIP_SETTING");
        public final static Property Gender = new Property(6, int.class, "gender", false, "GENDER");
        public final static Property Important = new Property(7, int.class, "important", false, "IMPORTANT");
        public final static Property Remark = new Property(8, String.class, "remark", false, "REMARK");
        public final static Property CreateTime = new Property(9, long.class, "createTime", false, "CREATE_TIME");
        public final static Property Address = new Property(10, String.class, "address", false, "ADDRESS");
        public final static Property AvatarPath = new Property(11, String.class, "avatarPath", false, "AVATAR_PATH");
        public final static Property Relationship = new Property(12, int.class, "relationship", false, "RELATIONSHIP");
        public final static Property GroupInfo = new Property(13, int.class, "groupInfo", false, "GROUP_INFO");
        public final static Property GroupName = new Property(14, String.class, "groupName", false, "GROUP_NAME");
    };


    public DBUserBeanDao(DaoConfig config) {
        super(config);
    }
    
    public DBUserBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DBUSER_BEAN\" (" + //
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
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DBUSER_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DBUserBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUserId());
        stmt.bindLong(2, entity.getUserKey());
        stmt.bindLong(3, entity.getStatus());
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(4, phone);
        }
        stmt.bindLong(5, entity.getBirthday());
        stmt.bindLong(6, entity.getTipSetting());
        stmt.bindLong(7, entity.getGender());
        stmt.bindLong(8, entity.getImportant());
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(9, remark);
        }
        stmt.bindLong(10, entity.getCreateTime());
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(11, address);
        }
 
        String avatarPath = entity.getAvatarPath();
        if (avatarPath != null) {
            stmt.bindString(12, avatarPath);
        }
        stmt.bindLong(13, entity.getRelationship());
        stmt.bindLong(14, entity.getGroupInfo());
 
        String groupName = entity.getGroupName();
        if (groupName != null) {
            stmt.bindString(15, groupName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DBUserBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUserId());
        stmt.bindLong(2, entity.getUserKey());
        stmt.bindLong(3, entity.getStatus());
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(4, phone);
        }
        stmt.bindLong(5, entity.getBirthday());
        stmt.bindLong(6, entity.getTipSetting());
        stmt.bindLong(7, entity.getGender());
        stmt.bindLong(8, entity.getImportant());
 
        String remark = entity.getRemark();
        if (remark != null) {
            stmt.bindString(9, remark);
        }
        stmt.bindLong(10, entity.getCreateTime());
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(11, address);
        }
 
        String avatarPath = entity.getAvatarPath();
        if (avatarPath != null) {
            stmt.bindString(12, avatarPath);
        }
        stmt.bindLong(13, entity.getRelationship());
        stmt.bindLong(14, entity.getGroupInfo());
 
        String groupName = entity.getGroupName();
        if (groupName != null) {
            stmt.bindString(15, groupName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public DBUserBean readEntity(Cursor cursor, int offset) {
        DBUserBean entity = new DBUserBean( //
            cursor.getLong(offset + 0), // userId
            cursor.getLong(offset + 1), // userKey
            cursor.getInt(offset + 2), // status
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // phone
            cursor.getLong(offset + 4), // birthday
            cursor.getInt(offset + 5), // tipSetting
            cursor.getInt(offset + 6), // gender
            cursor.getInt(offset + 7), // important
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // remark
            cursor.getLong(offset + 9), // createTime
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // address
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // avatarPath
            cursor.getInt(offset + 12), // relationship
            cursor.getInt(offset + 13), // groupInfo
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14) // groupName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DBUserBean entity, int offset) {
        entity.setUserId(cursor.getLong(offset + 0));
        entity.setUserKey(cursor.getLong(offset + 1));
        entity.setStatus(cursor.getInt(offset + 2));
        entity.setPhone(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setBirthday(cursor.getLong(offset + 4));
        entity.setTipSetting(cursor.getInt(offset + 5));
        entity.setGender(cursor.getInt(offset + 6));
        entity.setImportant(cursor.getInt(offset + 7));
        entity.setRemark(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCreateTime(cursor.getLong(offset + 9));
        entity.setAddress(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setAvatarPath(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setRelationship(cursor.getInt(offset + 12));
        entity.setGroupInfo(cursor.getInt(offset + 13));
        entity.setGroupName(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DBUserBean entity, long rowId) {
        entity.setUserId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DBUserBean entity) {
        if(entity != null) {
            return entity.getUserId();
        } else {
            return null;
        }
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
