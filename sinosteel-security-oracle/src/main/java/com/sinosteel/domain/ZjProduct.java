package com.sinosteel.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 *法人对象模型
 */
@Entity
@Table(name = "zj_product")
public class ZjProduct extends BaseEntity {

    @Column(name = "ZZJGDM")
    private String zzjgdm;// 组织机构代码
    @Column(name = "ORGAN_NM")
    private String organName;//组织机构名称
    @Column(name = "ECONOMY_TYPE")
    private Long economyType;//经济类型
    @Column(name = "FILLTIME")
    private Date fillTime;// 申请日期
    @Column(name = "PROD_NM")
    private String productName;//产品名称
    @Column(name = "PROD_MODEL_CH")
    private String cell;// 产品型号
    @Column(name = "STAND_NO")
    private String standNo;//标准号
    @Column(name = "STAND_NM")
    private String standName;//标准名称
    @Column(name = "CERTNUM")
    private String certNum;//证书号
    @Column(name = "INFILEDATE")
    private Date infileDate;// 归档日期

    public String getZzjgdm() {
        return zzjgdm;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm;
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public Long getEconomyType() {
        return economyType;
    }

    public void setEconomyType(Long economyType) {
        this.economyType = economyType;
    }

    public Date getFillTime() {
        return fillTime;
    }

    public void setFillTime(Date fillTime) {
        this.fillTime = fillTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getStandNo() {
        return standNo;
    }

    public void setStandNo(String standNo) {
        this.standNo = standNo;
    }

    public String getStandName() {
        return standName;
    }

    public void setStandName(String standName) {
        this.standName = standName;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public Date getInfileDate() {
        return infileDate;
    }

    public void setInfileDate(Date infileDate) {
        this.infileDate = infileDate;
    }
}
