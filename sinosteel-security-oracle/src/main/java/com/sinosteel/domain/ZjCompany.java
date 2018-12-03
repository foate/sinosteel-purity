package com.sinosteel.domain;

/**
 * 企业对象模型
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sinosteel.domain.dictionary.ZjDictCommon;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "zj_company")
public class ZjCompany extends BaseEntity {

    @Column(name = "TYSHXYDM")
    private String tyshxydm; //社会统一信用代码
    @Column(name = "XH")
    private Long xh; //序号
    @Column(name = "ZZJGDM")
    private String zzjgdm; //组织机构代码
    @Column(name = "DWMC")
    private String companyName; //单位名称
    @Column(name = "JGLX")
    private String jglx; //机构类型
    @Column(name = "FDDBR")
    private String legalPerson; //法人
    @Column(name = "ZJLX")
    private String zjlx; //证件类型
    @Column(name = "FDDBRHM")
    private String legalPersonNo; //法人代表号码
    @Column(name = "JYFW")
    private String jyfw; // 经营范围
    @Column(name = "JJLX")
    private String jjlx; //
    @Column(name = "ZGDM")
    private String zgdm; //
    @Column(name = "XZQH")
    private String xzqh; //
    @Column(name = "ZCDZ")
    private String zcdz; // 注册地址
    @Column(name = "YZBM")
    private String yzbm; // 邮政编码
    @Column(name = "BZRQ")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date bzrq;//
    @Column(name = "ZFRQ")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date zfrq;//
    @Column(name = "ZYCP")
    private String zycp; //
    @Column(name = "ZCZJ")
    private Double zczj; // 注册资金
    @Column(name = "HBZL")
    private String hbzl; //
    @Column(name = "WFTZGB")
    private String wftzgb; //
    @Column(name = "ZGRS")
    private Integer zgrs; //
    @Column(name = "BGRQ")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date bgrq;//
    @Column(name = "NJRQ")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date njrq;//
    @Column(name = "NJQX")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date njqx;//
    @Column(name = "ZCH")
    private String zch; //
    @Column(name = "ZGBM")
    private String zgbm; //
    @Column(name = "PZJGMC")
    private String pzjgmc; //
    @Column(name = "PZWH")
    private String pzwh; //
    @Column(name = "GK")
    private String gk; //
    @Column(name = "EMAIL")
    private String email; //
    @Column(name = "WZ")
    private String wz; //
    @Column(name = "YDDH")
    private String yddh; //
    @Column(name = "XJJHY")
    private String xjjhy; //
    @Column(name = "JYDZ")
    private String jydz; //
    @Column(name = "SSZB")
    private String sszb; //
    @Column(name = "DH")
    private String dh; //
    @Column(name = "SHRQ")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date shrq;//
    @Column(name = "DJPZJG")
    private String djpzjg; //
    @Column(name = "CK")
    private String ck; //
    @Column(name = "JBR")
    private String jbr; //
    @Column(name = "JYYZBM")
    private String jyyzbm; //
    @Column(name = "CLRQ")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date clrq;//
    @Column(name = "ZYGD")
    private String zygd; //
    @Column(name = "HY")
    private String hy; //
    @Column(name = "BZJG")
    private String bzjg; //
    @Column(name = "SLBZ")
    private Long slbz; //
    @Column(name = "SL")
    private Long sl; //
    @Column(name = "SLBZZS")
    private Long slbzzs; //
    @Column(name = "SLBZJD")
    private Long slbzjd; //
    @Column(name = "SLBZYQ")
    private Long slbzyq; //
    @Column(name = "SLBZJB")
    private Long slbzjb; //
    @Column(name = "SLBZGW")
    private Long slbzgw; //
    @Column(name = "SLBZSB")
    private Long slbzsb; //
    @Column(name = "SLBZZSBZ")
    private Long slbzzsbz; //
    @Column(name = "SLBZJDBZ")
    private Long slbzjdbz; //
    @Column(name = "SLBZYQBZ")
    private Long slbzyqbz; //
    @Column(name = "SLBZJBBZ")
    private Long slbzjbbz; //
    @Column(name = "SLBZGWBZ")
    private Long slbzgwbz; //
    @Column(name = "SLBZSBBZ")
    private Long slbzsbbz; //
    @Column(name = "SLBZPXBZ")
    private Long slbzpxbz; //


    public String getTyshxydm() {
        return tyshxydm;
    }

    public void setTyshxydm(String tyshxydm) {
        this.tyshxydm = tyshxydm;
    }

    public Long getXh() {
        return xh;
    }

    public void setXh(Long xh) {
        this.xh = xh;
    }

    public String getZzjgdm() {
        return zzjgdm;
    }

    public void setZzjgdm(String zzjgdm) {
        this.zzjgdm = zzjgdm;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJglx() {
        return jglx;
    }

    public void setJglx(String jglx) {
        this.jglx = jglx;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    public String getLegalPersonNo() {
        return legalPersonNo;
    }

    public void setLegalPersonNo(String legalPersonNo) {
        this.legalPersonNo = legalPersonNo;
    }

    public String getJyfw() {
        return jyfw;
    }

    public void setJyfw(String jyfw) {
        this.jyfw = jyfw;
    }

    public String getJjlx() {
        return jjlx;
    }

    public void setJjlx(String jjlx) {
        this.jjlx = jjlx;
    }

    public String getZgdm() {
        return zgdm;
    }

    public void setZgdm(String zgdm) {
        this.zgdm = zgdm;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getZcdz() {
        return zcdz;
    }

    public void setZcdz(String zcdz) {
        this.zcdz = zcdz;
    }

    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

    public Date getBzrq() {
        return bzrq;
    }

    public void setBzrq(Date bzrq) {
        this.bzrq = bzrq;
    }

    public Date getZfrq() {
        return zfrq;
    }

    public void setZfrq(Date zfrq) {
        this.zfrq = zfrq;
    }

    public String getZycp() {
        return zycp;
    }

    public void setZycp(String zycp) {
        this.zycp = zycp;
    }

    public Double getZczj() {
        return zczj;
    }

    public void setZczj(Double zczj) {
        this.zczj = zczj;
    }

    public String getHbzl() {
        return hbzl;
    }

    public void setHbzl(String hbzl) {
        this.hbzl = hbzl;
    }

    public String getWftzgb() {
        return wftzgb;
    }

    public void setWftzgb(String wftzgb) {
        this.wftzgb = wftzgb;
    }

    public Integer getZgrs() {
        return zgrs;
    }

    public void setZgrs(Integer zgrs) {
        this.zgrs = zgrs;
    }

    public Date getBgrq() {
        return bgrq;
    }

    public void setBgrq(Date bgrq) {
        this.bgrq = bgrq;
    }

    public Date getNjrq() {
        return njrq;
    }

    public void setNjrq(Date njrq) {
        this.njrq = njrq;
    }

    public Date getNjqx() {
        return njqx;
    }

    public void setNjqx(Date njqx) {
        this.njqx = njqx;
    }

    public String getZch() {
        return zch;
    }

    public void setZch(String zch) {
        this.zch = zch;
    }

    public String getZgbm() {
        return zgbm;
    }

    public void setZgbm(String zgbm) {
        this.zgbm = zgbm;
    }

    public String getPzjgmc() {
        return pzjgmc;
    }

    public void setPzjgmc(String pzjgmc) {
        this.pzjgmc = pzjgmc;
    }

    public String getPzwh() {
        return pzwh;
    }

    public void setPzwh(String pzwh) {
        this.pzwh = pzwh;
    }

    public String getGk() {
        return gk;
    }

    public void setGk(String gk) {
        this.gk = gk;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWz() {
        return wz;
    }

    public void setWz(String wz) {
        this.wz = wz;
    }

    public String getYddh() {
        return yddh;
    }

    public void setYddh(String yddh) {
        this.yddh = yddh;
    }

    public String getXjjhy() {
        return xjjhy;
    }

    public void setXjjhy(String xjjhy) {
        this.xjjhy = xjjhy;
    }

    public String getJydz() {
        return jydz;
    }

    public void setJydz(String jydz) {
        this.jydz = jydz;
    }

    public String getSszb() {
        return sszb;
    }

    public void setSszb(String sszb) {
        this.sszb = sszb;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public Date getShrq() {
        return shrq;
    }

    public void setShrq(Date shrq) {
        this.shrq = shrq;
    }

    public String getDjpzjg() {
        return djpzjg;
    }

    public void setDjpzjg(String djpzjg) {
        this.djpzjg = djpzjg;
    }

    public String getCk() {
        return ck;
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public String getJbr() {
        return jbr;
    }

    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public String getJyyzbm() {
        return jyyzbm;
    }

    public void setJyyzbm(String jyyzbm) {
        this.jyyzbm = jyyzbm;
    }

    public Date getClrq() {
        return clrq;
    }

    public void setClrq(Date clrq) {
        this.clrq = clrq;
    }

    public String getZygd() {
        return zygd;
    }

    public void setZygd(String zygd) {
        this.zygd = zygd;
    }

    public String getHy() {
        return hy;
    }

    public void setHy(String hy) {
        this.hy = hy;
    }

    public String getBzjg() {
        return bzjg;
    }

    public void setBzjg(String bzjg) {
        this.bzjg = bzjg;
    }

    public Long getSlbz() {
        return slbz;
    }

    public void setSlbz(Long slbz) {
        this.slbz = slbz;
    }

    public Long getSl() {
        return sl;
    }

    public void setSl(Long sl) {
        this.sl = sl;
    }

    public Long getSlbzzs() {
        return slbzzs;
    }

    public void setSlbzzs(Long slbzzs) {
        this.slbzzs = slbzzs;
    }

    public Long getSlbzjd() {
        return slbzjd;
    }

    public void setSlbzjd(Long slbzjd) {
        this.slbzjd = slbzjd;
    }

    public Long getSlbzyq() {
        return slbzyq;
    }

    public void setSlbzyq(Long slbzyq) {
        this.slbzyq = slbzyq;
    }

    public Long getSlbzjb() {
        return slbzjb;
    }

    public void setSlbzjb(Long slbzjb) {
        this.slbzjb = slbzjb;
    }

    public Long getSlbzgw() {
        return slbzgw;
    }

    public void setSlbzgw(Long slbzgw) {
        this.slbzgw = slbzgw;
    }

    public Long getSlbzsb() {
        return slbzsb;
    }

    public void setSlbzsb(Long slbzsb) {
        this.slbzsb = slbzsb;
    }

    public Long getSlbzzsbz() {
        return slbzzsbz;
    }

    public void setSlbzzsbz(Long slbzzsbz) {
        this.slbzzsbz = slbzzsbz;
    }

    public Long getSlbzjdbz() {
        return slbzjdbz;
    }

    public void setSlbzjdbz(Long slbzjdbz) {
        this.slbzjdbz = slbzjdbz;
    }

    public Long getSlbzyqbz() {
        return slbzyqbz;
    }

    public void setSlbzyqbz(Long slbzyqbz) {
        this.slbzyqbz = slbzyqbz;
    }

    public Long getSlbzjbbz() {
        return slbzjbbz;
    }

    public void setSlbzjbbz(Long slbzjbbz) {
        this.slbzjbbz = slbzjbbz;
    }

    public Long getSlbzgwbz() {
        return slbzgwbz;
    }

    public void setSlbzgwbz(Long slbzgwbz) {
        this.slbzgwbz = slbzgwbz;
    }

    public Long getSlbzsbbz() {
        return slbzsbbz;
    }

    public void setSlbzsbbz(Long slbzsbbz) {
        this.slbzsbbz = slbzsbbz;
    }

    public Long getSlbzpxbz() {
        return slbzpxbz;
    }

    public void setSlbzpxbz(Long slbzpxbz) {
        this.slbzpxbz = slbzpxbz;
    }

}
