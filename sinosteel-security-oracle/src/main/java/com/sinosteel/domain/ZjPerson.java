package com.sinosteel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 *法人对象模型
 */
@Entity
@Table(name = "zj_person")
public class ZjPerson extends BaseEntity {

    @Column(name = "SEX")
    private String sex;//性别
    @Column(name = "NATION")
    private String nation;//民族
    @Column(name = "ZJLX")
    private String zjlx;//证件类型，关联码表
    @Column(name = "ZJHM")
    private String zjhm;//证件号码
    @Column(name = "BIRTHDAY")
    private Date birthday;//出生日期
    @Column(name = "XL")
    private String education;//学历
    @Column(name = "XW")
    private String xw;//学位
    @Column(name = "ORGAN_ID")
    private String organId;//所属组织机构代码
    @Column(name = "TXDZ")
    private String txdz;//通讯地址
    @Column(name = "YB")
    private String yb;//邮编
    @Column(name = "LXFS")
    private String lxfs;//联系方式
    @Column(name = "EMAIL")
    private String email;//电子邮箱
    @Column(name = "ZY")
    private String zy;//所学专业
    @Column(name = "ORGAN_NAME")
    private String organName;//组织机构名称
    @Column(name = "ZC")
    private String zc;//职称

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getXw() {
        return xw;
    }

    public void setXw(String xw) {
        this.xw = xw;
    }

    public String getOrganId() {
        return organId;
    }

    public void setOrganId(String organId) {
        this.organId = organId;
    }

    public String getTxdz() {
        return txdz;
    }

    public void setTxdz(String txdz) {
        this.txdz = txdz;
    }

    public String getYb() {
        return yb;
    }

    public void setYb(String yb) {
        this.yb = yb;
    }

    public String getLxfs() {
        return lxfs;
    }

    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }
}
