package vbs.vvi.com.bs.bean;

import android.graphics.Bitmap;

/**
 * Created by Wayne on 2016/7/29.
 * Email: loveuu715@163.com
 */
public class ContactBean {
    private String name;
    private String contactId;
    private String phoneNum;
    private Bitmap contactAvatar;

    public ContactBean(String name, String contactId, String phoneNum, Bitmap contactAvatar) {
        this.name = name;
        this.contactId = contactId;
        this.phoneNum = phoneNum;
        this.contactAvatar = contactAvatar;
    }

    public ContactBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Bitmap getContactAvatar() {
        return contactAvatar;
    }

    public void setContactAvatar(Bitmap contactAvatar) {
        this.contactAvatar = contactAvatar;
    }
}
