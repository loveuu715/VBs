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
    private long userId;//用户id
    private String userKey;//用户唯一key
    private int status;//当前状态 0.未同步 1.已同步
    private String name;//姓名
    private String phone;//手机
    private long birthday;//生日 时间戳形式
    private int tipSetting;//提醒类型:0 1 2 待定
    private int gender;//性别 0 男 1 女
    private int important;//是否重点关注 0 否 1 是
    private String remark;//备注
    private long createTime;//创建时间戳
    private String address;//地址
    private String avatarPath;//头像地址
    private int relationship;//关系类型:0 1 2 3 待定
    private int groupInfo;//分组类型:0 1 2 3 待定
    private String groupName;//组名
    private int year;//生日年份
    private int month;//生日月份
    private int day;//生日天
    private int birthType;//生日类型: 0 公历 1 农历

    @Generated(hash = 639319968)
    public DBUserBean(long userId, String userKey, int status, String name,
                      String phone, long birthday, int tipSetting, int gender, int important,
                      String remark, long createTime, String address, String avatarPath,
                      int relationship, int groupInfo, String groupName, int year, int month,
                      int day, int birthType) {
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
        this.year = year;
        this.month = month;
        this.day = day;
        this.birthType = birthType;
    }

    @Generated(hash = 613699683)
    public DBUserBean() {
    }

    public int getBirthType() {
        return this.birthType;
    }

    public void setBirthType(int birthType) {
        this.birthType = birthType;
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getGroupInfo() {
        return this.groupInfo;
    }

    public void setGroupInfo(int groupInfo) {
        this.groupInfo = groupInfo;
    }

    public int getRelationship() {
        return this.relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = relationship;
    }

    public String getAvatarPath() {
        return this.avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getImportant() {
        return this.important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getTipSetting() {
        return this.tipSetting;
    }

    public void setTipSetting(int tipSetting) {
        this.tipSetting = tipSetting;
    }

    public long getBirthday() {
        return this.birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserKey() {
        return this.userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

}
