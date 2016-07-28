package vbs.vvi.com.bs.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Wayne on 2016/7/21.
 */
@Entity
public class DBUserBean {
    @Id(autoincrement = true)
    private long userId;
    private String userKey;
    private int status;
    private String name;
    private String phone;
    private long birthday;
    private int tipSetting;
    private int gender;
    private int important;
    private String remark;
    private long createTime;
    private String address;
    private String avatarPath;
    private int relationship;
    private int groupInfo;
    private String groupName;

    @Generated(hash = 613699683)
    public DBUserBean() {
    }

    @Generated(hash = 1420592871)
    public DBUserBean(long userId, String userKey, int status, String name, String phone,
                      long birthday, int tipSetting, int gender, int important, String remark,
                      long createTime, String address, String avatarPath, int relationship,
                      int groupInfo, String groupName) {
        this.userId = userId;
        this.userKey = userKey;
        this.status = status;
        this.name = name;
        this.phone = phone;
        this.birthday = birthday;
        this.tipSetting = tipSetting;
        this.gender = gender;
        this.important = important;
        this.remark = remark;
        this.createTime = createTime;
        this.address = address;
        this.avatarPath = avatarPath;
        this.relationship = relationship;
        this.groupInfo = groupInfo;
        this.groupName = groupName;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public int getTipSetting() {
        return tipSetting;
    }

    public void setTipSetting(int tipSetting) {
        this.tipSetting = tipSetting;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public int getRelationship() {
        return relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = relationship;
    }

    public int getGroupInfo() {
        return groupInfo;
    }

    public void setGroupInfo(int groupInfo) {
        this.groupInfo = groupInfo;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
