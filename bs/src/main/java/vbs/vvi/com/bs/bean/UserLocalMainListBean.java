package vbs.vvi.com.bs.bean;

/**
 * Created by Wayne on 2016/7/26.
 * Email: loveuu715@163.com
 */
public class UserLocalMainListBean {
    private String name;
    private int gender;
    private String remark;
    private long birthdayStamp;
    private String avatarPath;

    public UserLocalMainListBean(String name, int gender, String remark, long birthdayStamp, String avatarPath) {
        this.name = name;
        this.gender = gender;
        this.remark = remark;
        this.birthdayStamp = birthdayStamp;
        this.avatarPath = avatarPath;
    }

    public UserLocalMainListBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getBirthdayStamp() {
        return birthdayStamp;
    }

    public void setBirthdayStamp(long birthdayStamp) {
        this.birthdayStamp = birthdayStamp;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
