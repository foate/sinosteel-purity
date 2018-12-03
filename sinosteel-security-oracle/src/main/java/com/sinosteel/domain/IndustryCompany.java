package com.sinosteel.domain;

/**
 * 企业对象模型
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "industry_company")
public class IndustryCompany extends BaseEntity {

    @Column(name = "ORGNAME")
    private String orgName; //单位名称
    @Column(name = "POST")
    private String post; //邮政编码
    @Column(name = "CONMAN")
    private String conMan; //联系人
    @Column(name = "CONTEL")
    private String conTel; //联系电话
    @Column(name = "CONMANMOBILE")
    private String conManMobile; //联系手机
    @Column(name = "APPLYDATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date applyDate;// 申请时间
    @Column(name = "CONFAX")
    private String conFax; // 传真
    @Column(name = "CONEMAIL")
    private String conEmail; // E-MAIL
    @Column(name = "ORGANNO")
    private String organno; //组织机构代码（社会统一信用代码）
    @Column(name = "ECONOMYTYPE")
    private String economyType; //经济类型，见经济类型字典表
    @Column(name = "ECONOMYTYPECONTENT")
    private String economyTypeContent; // 经济类型内容
    @Column(name = "BUSREGORG")
    private String busRegOrg; //  工商登记机构
    @Column(name = "BUSLICENO")
    private String busLiceNo; //  营业执照注册号
    @Column(name = "FOUNDDATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date foundDate;// 成立日期
    @Column(name = "DEALINPERIODS")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dealInPeriodS;//经营期限（开始时间）
    @Column(name = "DEALINPERIODE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date dealInPeriodE;//经营期限（结束时间）
    @Column(name = "FIXEDASSETS")
    private BigDecimal fixedAssets;// 固定资产（万元）
    @Column(name = "REGCAPITAL")
    private BigDecimal regCapital;// 注册资金（万元）
    @Column(name = "YEARTOTALOUTPUT")
    private BigDecimal yearTotalOutPut;// 年总产值（万元）
    @Column(name = "YEARSALE")
    private BigDecimal yearSale;// 年销售额（万元）
    @Column(name = "YEARGAIN")
    private BigDecimal yearGain;// 年利润（万元）
    @Column(name = "YEARTAX")
    private BigDecimal yearTax;// 年缴税金额（万元）
    @Column(name = "SINGLEMAN")
    private String singLeMan; //  法定代表人
    @Column(name = "ENTPRINC")
    private String entprinc; //  企业负责人
    @Column(name = "QUALITYPLEDGEMAN")
    private String qualityPledgeMan; // 质量保证负责人
    @Column(name = "WORKERNUM")
    private BigDecimal workerNum;// 从业人员总数
    @Column(name = "PROTECHMAN")
    private BigDecimal protechMan;// 专业技术人员数
    @Column(name = "PROJINVEST")
    private BigDecimal projInvest;// 项目总投资
    @Column(name = "DESIGNABILITY")
    private String designability; //  年设计生产能力
    @Column(name = "EXPLAIN")
    private String explain; // 其他需要说明的情况
    @Column(name = "ADDR")
    private String addr; //  住所
    @Column(name = "ADDRNO")
    private BigDecimal addrNo;// 住所行政区划
    @Column(name = "SCALE")
    private String scale; //  企业规模，字典
    @Column(name = "SCALECONTENT")
    private String scaleContent; //  企业规模
    @Column(name = "ISXUKE")
    private String isXuke; //  1是0否 是否生产许可证管理企业
    @Column(name = "ISEDIT")
    private String isEdit; //  1允许编辑，0不允许编辑
    @Column(name = "LOGINID")
    private String loginId; //  登录用户名
    @Column(name = "PASSWORD")
    private String password; //  登录密码
    @Column(name = "PERSONID")
    private String personId; // 法人身份证号
    @Column(name = "JYFW")
    private String jyfw; //  经营范围
    @Column(name = "XJJHY")
    private String xjjhy; // 新经济行业
    @Column(name = "XJJHYCONTENT")
    private String xjjhyContent; //  新经济行业内容
    @Column(name = "REGION")
    private String region; // 行政区划内容
    @Column(name = "OLDORGANID")
    private BigDecimal oldOrganId; // 行政许可系统的organid
    @Column(name = "REGIONNO")
    private String regionNo; // 行政区划
    @Column(name = "ISQZRZ")
    private String isQzrz; // 是否3c企业
    @Column(name = "ISSPXG")
    private String isSpxg; // 是否食品相关企业
    @Column(name = "UPDATEDATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date updateDate;//数据更新时间
    @Column(name = "STATE")
    private String state; // 是否管辖1是0否
    @Column(name = "FLOAREA")
    private BigDecimal floarea; // 占地面积
    @Column(name = "STRAREA")
    private BigDecimal strarea; // 建筑面积
    @Column(name = "WORKFUND")
    private BigDecimal workfund; // 流动资金
    @Column(name = "ORGANCLASS")
    private String organClass; // 企业分类
    @Column(name = "CLASSFRE")
    private String classFre; // 建议巡查次数
    @Column(name = "ISDINGJIAN")
    private String isDingJian; // 是否定检企业
    @Column(name = "ISKEY")
    private String isKey; // 是否重点监管产品企业
    @Column(name = "RZXCODE")
    private String rzxCode; //认证项
    @Column(name = "JGTYPE")
    private String jgType; // 监管类别
    @Column(name = "NSGLNUM")
    private BigDecimal nsglNum; // 年审次数
    @Column(name = "WGJGNUM")
    private BigDecimal wgjgNum; // 委托加工备案次数
    @Column(name = "XZCFNUM")
    private BigDecimal xzcfNum; // 行政处罚次数
    @Column(name = "SJJCNUM1")
    private BigDecimal sjjcNum1; // 省级监督检查合格次数
    @Column(name = "SJJCNUM0")
    private BigDecimal sjjcNum0; // 省级监督检查不合格次数
    @Column(name = "RCJCNUM1")
    private BigDecimal rcjcNum1; // 日常巡查合格次数
    @Column(name = "RCJCNUM0")
    private BigDecimal rcjcNum0; // 日常巡查不合格次数
    @Column(name = "SHIJIJCNUM1")
    private BigDecimal shijijcNum1; // 市级监督检查合格次数
    @Column(name = "SHIJIJCNUM0")
    private BigDecimal shijijcNum0; // 市级监督检查不合格次数
    @Column(name = "NSJCNUM1")
    private BigDecimal nsjcNum1; // 年审检查合格次数
    @Column(name = "NSJCNUM0")
    private BigDecimal nsjcNum0; // 年审检查不合格次数
    @Column(name = "ZXJCNUM1")
    private BigDecimal zxjcNum1; // 专项检查合格次数
    @Column(name = "ZXJCNUM0")
    private BigDecimal zxjcNum0; // 专项检查不合格次数
    @Column(name = "GCJYNUM1")
    private BigDecimal gcjyNum1; // 国抽检验合格次数
    @Column(name = "GCJYNUM0")
    private BigDecimal gcjyNum0; // 国抽检验不合格次数
    @Column(name = "SCJYNUM1")
    private BigDecimal scjyNum1; // 省抽检验合格次数
    @Column(name = "SCJYNUM0")
    private BigDecimal scjyNum0; // 省抽检验不合格次数
    @Column(name = "DJJYNUM1")
    private BigDecimal djjyNum1; // 定检检验合格次数
    @Column(name = "DJJYNUM0")
    private BigDecimal djjyNum0; // 定检检验不合格次数
    @Column(name = "SHICHOUJYNUM1")
    private BigDecimal shichoujyNum1; // 市抽检验合格次数
    @Column(name = "SHICHOUJYNUM0")
    private BigDecimal shichoujyNum0; // 市抽检验不合格次数
    @Column(name = "XCJYNUM1")
    private BigDecimal xcjyNum1; // 县抽检验合格次数
    @Column(name = "XCJYNUM0")
    private BigDecimal xcjyNum0; // 县抽检验不合格次数
    @Column(name = "ZXJYNUM1")
    private BigDecimal zxjyNum1; // 专项检验合格次数
    @Column(name = "ZXJYNUM0")
    private BigDecimal zxjyNum0; // 专项检验不合格次数
    @Column(name = "ISZHILIANG")
    private String isZhiLiang; // 是否质量奖
    @Column(name = "ISMINGPAI")
    private String isMingPai; // 是否名牌
    @Column(name = "ISRICHANG")
    private String isRiChang; // 是否日常消费品企业
    @Column(name = "ZLSSNUM")
    private BigDecimal zlssNum; // 质量申诉次数
    @Column(name = "CPZHNUM")
    private BigDecimal cpzhNum; // 产品召回次数
    @Column(name = "CANCELLATION")
    private String cancelLation; // 是否注销 不填或者0为正常  4为注销
    @Column(name = "RZORGAN")
    private String rzOrgan; // 认证机构
    @Column(name = "ZHUANKEMAN")
    private BigDecimal zhuankeMan; //专业技术人员专科人数
    @Column(name = "BENKEMAN")
    private BigDecimal benkeMan; // 专业技术人员本科人数
    @Column(name = "CERTSITUATION")
    private String certsituation; //生产许可获证情况
    @Column(name = "QITARZX")
    private String qitarzx; // 其他认证项（手写）
    @Column(name = "ISSALE")
    private String isSale; // 是否纯销售企业1是0否
    @Column(name = "POINT")
    private String point; // 住所百度坐标地址
    @Column(name = "LASTEDITTIME")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date lastEditTime;//最后修改时间
    @Column(name = "LASTEDITER")
    private String lastEditEr; // 最后修改单位名称
    @Column(name = "LASTEDITID")
    private BigDecimal lastEditId; // 最后修改单位id
    @Column(name = "ISHESHI")
    private String isHeShi; // 是否核实1是0否
    @Column(name = "ISJIANCAI")
    private String isJianCai; // 建材生产企业
    @Column(name = "ISYINGER")
    private String isYingEr; // 婴幼儿
    @Column(name = "ISSPXGFFZ")
    private String isSpxgFfz; // 食品相关（非发证）
    @Column(name = "CREATEDATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createDate;//创建时间,企业进库时间
    @Column(name = "LINGYUCODE")
    private String lingYuCode; // 所属领域
    @Column(name = "LINGYUNAME")
    private String lingYuName; // 所属领域
    @Column(name = "ETL_DATE")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date etlDate;//创建时间
    @Column(name = "COUNTYNAME")
    private String countyName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getConMan() {
        return conMan;
    }

    public void setConMan(String conMan) {
        this.conMan = conMan;
    }

    public String getConTel() {
        return conTel;
    }

    public void setConTel(String conTel) {
        this.conTel = conTel;
    }

    public String getConManMobile() {
        return conManMobile;
    }

    public void setConManMobile(String conManMobile) {
        this.conManMobile = conManMobile;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getConFax() {
        return conFax;
    }

    public void setConFax(String conFax) {
        this.conFax = conFax;
    }

    public String getConEmail() {
        return conEmail;
    }

    public void setConEmail(String conEmail) {
        this.conEmail = conEmail;
    }

    public String getOrganno() {
        return organno;
    }

    public void setOrganno(String organno) {
        this.organno = organno;
    }

    public String getEconomyType() {
        return economyType;
    }

    public void setEconomyType(String economyType) {
        this.economyType = economyType;
    }

    public String getEconomyTypeContent() {
        return economyTypeContent;
    }

    public void setEconomyTypeContent(String economyTypeContent) {
        this.economyTypeContent = economyTypeContent;
    }

    public String getBusRegOrg() {
        return busRegOrg;
    }

    public void setBusRegOrg(String busRegOrg) {
        this.busRegOrg = busRegOrg;
    }

    public String getBusLiceNo() {
        return busLiceNo;
    }

    public void setBusLiceNo(String busLiceNo) {
        this.busLiceNo = busLiceNo;
    }

    public Date getFoundDate() {
        return foundDate;
    }

    public void setFoundDate(Date foundDate) {
        this.foundDate = foundDate;
    }

    public Date getDealInPeriodS() {
        return dealInPeriodS;
    }

    public void setDealInPeriodS(Date dealInPeriodS) {
        this.dealInPeriodS = dealInPeriodS;
    }

    public Date getDealInPeriodE() {
        return dealInPeriodE;
    }

    public void setDealInPeriodE(Date dealInPeriodE) {
        this.dealInPeriodE = dealInPeriodE;
    }

    public BigDecimal getFixedAssets() {
        return fixedAssets;
    }

    public void setFixedAssets(BigDecimal fixedAssets) {
        this.fixedAssets = fixedAssets;
    }

    public BigDecimal getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(BigDecimal regCapital) {
        this.regCapital = regCapital;
    }

    public BigDecimal getYearTotalOutPut() {
        return yearTotalOutPut;
    }

    public void setYearTotalOutPut(BigDecimal yearTotalOutPut) {
        this.yearTotalOutPut = yearTotalOutPut;
    }

    public BigDecimal getYearSale() {
        return yearSale;
    }

    public void setYearSale(BigDecimal yearSale) {
        this.yearSale = yearSale;
    }

    public BigDecimal getYearGain() {
        return yearGain;
    }

    public void setYearGain(BigDecimal yearGain) {
        this.yearGain = yearGain;
    }

    public BigDecimal getYearTax() {
        return yearTax;
    }

    public void setYearTax(BigDecimal yearTax) {
        this.yearTax = yearTax;
    }

    public String getSingLeMan() {
        return singLeMan;
    }

    public void setSingLeMan(String singLeMan) {
        this.singLeMan = singLeMan;
    }

    public String getEntprinc() {
        return entprinc;
    }

    public void setEntprinc(String entprinc) {
        this.entprinc = entprinc;
    }

    public String getQualityPledgeMan() {
        return qualityPledgeMan;
    }

    public void setQualityPledgeMan(String qualityPledgeMan) {
        this.qualityPledgeMan = qualityPledgeMan;
    }

    public BigDecimal getWorkerNum() {
        return workerNum;
    }

    public void setWorkerNum(BigDecimal workerNum) {
        this.workerNum = workerNum;
    }

    public BigDecimal getProtechMan() {
        return protechMan;
    }

    public void setProtechMan(BigDecimal protechMan) {
        this.protechMan = protechMan;
    }

    public BigDecimal getProjInvest() {
        return projInvest;
    }

    public void setProjInvest(BigDecimal projInvest) {
        this.projInvest = projInvest;
    }

    public String getDesignability() {
        return designability;
    }

    public void setDesignability(String designability) {
        this.designability = designability;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public BigDecimal getAddrNo() {
        return addrNo;
    }

    public void setAddrNo(BigDecimal addrNo) {
        this.addrNo = addrNo;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getScaleContent() {
        return scaleContent;
    }

    public void setScaleContent(String scaleContent) {
        this.scaleContent = scaleContent;
    }

    public String getIsXuke() {
        return isXuke;
    }

    public void setIsXuke(String isXuke) {
        this.isXuke = isXuke;
    }

    public String getIsEdit() {
        return isEdit;
    }

    public void setIsEdit(String isEdit) {
        this.isEdit = isEdit;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getJyfw() {
        return jyfw;
    }

    public void setJyfw(String jyfw) {
        this.jyfw = jyfw;
    }

    public String getXjjhy() {
        return xjjhy;
    }

    public void setXjjhy(String xjjhy) {
        this.xjjhy = xjjhy;
    }

    public String getXjjhyContent() {
        return xjjhyContent;
    }

    public void setXjjhyContent(String xjjhyContent) {
        this.xjjhyContent = xjjhyContent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getOldOrganId() {
        return oldOrganId;
    }

    public void setOldOrganId(BigDecimal oldOrganId) {
        this.oldOrganId = oldOrganId;
    }

    public String getRegionNo() {
        return regionNo;
    }

    public void setRegionNo(String regionNo) {
        this.regionNo = regionNo;
    }

    public String getIsQzrz() {
        return isQzrz;
    }

    public void setIsQzrz(String isQzrz) {
        this.isQzrz = isQzrz;
    }

    public String getIsSpxg() {
        return isSpxg;
    }

    public void setIsSpxg(String isSpxg) {
        this.isSpxg = isSpxg;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getFloarea() {
        return floarea;
    }

    public void setFloarea(BigDecimal floarea) {
        this.floarea = floarea;
    }

    public BigDecimal getStrarea() {
        return strarea;
    }

    public void setStrarea(BigDecimal strarea) {
        this.strarea = strarea;
    }

    public BigDecimal getWorkfund() {
        return workfund;
    }

    public void setWorkfund(BigDecimal workfund) {
        this.workfund = workfund;
    }

    public String getOrganClass() {
        return organClass;
    }

    public void setOrganClass(String organClass) {
        this.organClass = organClass;
    }

    public String getClassFre() {
        return classFre;
    }

    public void setClassFre(String classFre) {
        this.classFre = classFre;
    }

    public String getIsDingJian() {
        return isDingJian;
    }

    public void setIsDingJian(String isDingJian) {
        this.isDingJian = isDingJian;
    }

    public String getIsKey() {
        return isKey;
    }

    public void setIsKey(String isKey) {
        this.isKey = isKey;
    }

    public String getRzxCode() {
        return rzxCode;
    }

    public void setRzxCode(String rzxCode) {
        this.rzxCode = rzxCode;
    }

    public String getJgType() {
        return jgType;
    }

    public void setJgType(String jgType) {
        this.jgType = jgType;
    }

    public BigDecimal getNsglNum() {
        return nsglNum;
    }

    public void setNsglNum(BigDecimal nsglNum) {
        this.nsglNum = nsglNum;
    }

    public BigDecimal getWgjgNum() {
        return wgjgNum;
    }

    public void setWgjgNum(BigDecimal wgjgNum) {
        this.wgjgNum = wgjgNum;
    }

    public BigDecimal getXzcfNum() {
        return xzcfNum;
    }

    public void setXzcfNum(BigDecimal xzcfNum) {
        this.xzcfNum = xzcfNum;
    }

    public BigDecimal getSjjcNum1() {
        return sjjcNum1;
    }

    public void setSjjcNum1(BigDecimal sjjcNum1) {
        this.sjjcNum1 = sjjcNum1;
    }

    public BigDecimal getSjjcNum0() {
        return sjjcNum0;
    }

    public void setSjjcNum0(BigDecimal sjjcNum0) {
        this.sjjcNum0 = sjjcNum0;
    }

    public BigDecimal getRcjcNum1() {
        return rcjcNum1;
    }

    public void setRcjcNum1(BigDecimal rcjcNum1) {
        this.rcjcNum1 = rcjcNum1;
    }

    public BigDecimal getRcjcNum0() {
        return rcjcNum0;
    }

    public void setRcjcNum0(BigDecimal rcjcNum0) {
        this.rcjcNum0 = rcjcNum0;
    }

    public BigDecimal getShijijcNum1() {
        return shijijcNum1;
    }

    public void setShijijcNum1(BigDecimal shijijcNum1) {
        this.shijijcNum1 = shijijcNum1;
    }

    public BigDecimal getShijijcNum0() {
        return shijijcNum0;
    }

    public void setShijijcNum0(BigDecimal shijijcNum0) {
        this.shijijcNum0 = shijijcNum0;
    }

    public BigDecimal getNsjcNum1() {
        return nsjcNum1;
    }

    public void setNsjcNum1(BigDecimal nsjcNum1) {
        this.nsjcNum1 = nsjcNum1;
    }

    public BigDecimal getNsjcNum0() {
        return nsjcNum0;
    }

    public void setNsjcNum0(BigDecimal nsjcNum0) {
        this.nsjcNum0 = nsjcNum0;
    }

    public BigDecimal getZxjcNum1() {
        return zxjcNum1;
    }

    public void setZxjcNum1(BigDecimal zxjcNum1) {
        this.zxjcNum1 = zxjcNum1;
    }

    public BigDecimal getZxjcNum0() {
        return zxjcNum0;
    }

    public void setZxjcNum0(BigDecimal zxjcNum0) {
        this.zxjcNum0 = zxjcNum0;
    }

    public BigDecimal getGcjyNum1() {
        return gcjyNum1;
    }

    public void setGcjyNum1(BigDecimal gcjyNum1) {
        this.gcjyNum1 = gcjyNum1;
    }

    public BigDecimal getGcjyNum0() {
        return gcjyNum0;
    }

    public void setGcjyNum0(BigDecimal gcjyNum0) {
        this.gcjyNum0 = gcjyNum0;
    }

    public BigDecimal getScjyNum1() {
        return scjyNum1;
    }

    public void setScjyNum1(BigDecimal scjyNum1) {
        this.scjyNum1 = scjyNum1;
    }

    public BigDecimal getScjyNum0() {
        return scjyNum0;
    }

    public void setScjyNum0(BigDecimal scjyNum0) {
        this.scjyNum0 = scjyNum0;
    }

    public BigDecimal getDjjyNum1() {
        return djjyNum1;
    }

    public void setDjjyNum1(BigDecimal djjyNum1) {
        this.djjyNum1 = djjyNum1;
    }

    public BigDecimal getDjjyNum0() {
        return djjyNum0;
    }

    public void setDjjyNum0(BigDecimal djjyNum0) {
        this.djjyNum0 = djjyNum0;
    }

    public BigDecimal getShichoujyNum1() {
        return shichoujyNum1;
    }

    public void setShichoujyNum1(BigDecimal shichoujyNum1) {
        this.shichoujyNum1 = shichoujyNum1;
    }

    public BigDecimal getShichoujyNum0() {
        return shichoujyNum0;
    }

    public void setShichoujyNum0(BigDecimal shichoujyNum0) {
        this.shichoujyNum0 = shichoujyNum0;
    }

    public BigDecimal getXcjyNum1() {
        return xcjyNum1;
    }

    public void setXcjyNum1(BigDecimal xcjyNum1) {
        this.xcjyNum1 = xcjyNum1;
    }

    public BigDecimal getXcjyNum0() {
        return xcjyNum0;
    }

    public void setXcjyNum0(BigDecimal xcjyNum0) {
        this.xcjyNum0 = xcjyNum0;
    }

    public BigDecimal getZxjyNum1() {
        return zxjyNum1;
    }

    public void setZxjyNum1(BigDecimal zxjyNum1) {
        this.zxjyNum1 = zxjyNum1;
    }

    public BigDecimal getZxjyNum0() {
        return zxjyNum0;
    }

    public void setZxjyNum0(BigDecimal zxjyNum0) {
        this.zxjyNum0 = zxjyNum0;
    }

    public String getIsZhiLiang() {
        return isZhiLiang;
    }

    public void setIsZhiLiang(String isZhiLiang) {
        this.isZhiLiang = isZhiLiang;
    }

    public String getIsMingPai() {
        return isMingPai;
    }

    public void setIsMingPai(String isMingPai) {
        this.isMingPai = isMingPai;
    }

    public String getIsRiChang() {
        return isRiChang;
    }

    public void setIsRiChang(String isRiChang) {
        this.isRiChang = isRiChang;
    }

    public BigDecimal getZlssNum() {
        return zlssNum;
    }

    public void setZlssNum(BigDecimal zlssNum) {
        this.zlssNum = zlssNum;
    }

    public BigDecimal getCpzhNum() {
        return cpzhNum;
    }

    public void setCpzhNum(BigDecimal cpzhNum) {
        this.cpzhNum = cpzhNum;
    }

    public String getCancelLation() {
        return cancelLation;
    }

    public void setCancelLation(String cancelLation) {
        this.cancelLation = cancelLation;
    }

    public String getRzOrgan() {
        return rzOrgan;
    }

    public void setRzOrgan(String rzOrgan) {
        this.rzOrgan = rzOrgan;
    }

    public BigDecimal getZhuankeMan() {
        return zhuankeMan;
    }

    public void setZhuankeMan(BigDecimal zhuankeMan) {
        this.zhuankeMan = zhuankeMan;
    }

    public BigDecimal getBenkeMan() {
        return benkeMan;
    }

    public void setBenkeMan(BigDecimal benkeMan) {
        this.benkeMan = benkeMan;
    }

    public String getCertsituation() {
        return certsituation;
    }

    public void setCertsituation(String certsituation) {
        this.certsituation = certsituation;
    }

    public String getQitarzx() {
        return qitarzx;
    }

    public void setQitarzx(String qitarzx) {
        this.qitarzx = qitarzx;
    }

    public String getIsSale() {
        return isSale;
    }

    public void setIsSale(String isSale) {
        this.isSale = isSale;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public String getLastEditEr() {
        return lastEditEr;
    }

    public void setLastEditEr(String lastEditEr) {
        this.lastEditEr = lastEditEr;
    }

    public BigDecimal getLastEditId() {
        return lastEditId;
    }

    public void setLastEditId(BigDecimal lastEditId) {
        this.lastEditId = lastEditId;
    }

    public String getIsHeShi() {
        return isHeShi;
    }

    public void setIsHeShi(String isHeShi) {
        this.isHeShi = isHeShi;
    }

    public String getIsJianCai() {
        return isJianCai;
    }

    public void setIsJianCai(String isJianCai) {
        this.isJianCai = isJianCai;
    }

    public String getIsYingEr() {
        return isYingEr;
    }

    public void setIsYingEr(String isYingEr) {
        this.isYingEr = isYingEr;
    }

    public String getIsSpxgFfz() {
        return isSpxgFfz;
    }

    public void setIsSpxgFfz(String isSpxgFfz) {
        this.isSpxgFfz = isSpxgFfz;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLingYuCode() {
        return lingYuCode;
    }

    public void setLingYuCode(String lingYuCode) {
        this.lingYuCode = lingYuCode;
    }

    public String getLingYuName() {
        return lingYuName;
    }

    public void setLingYuName(String lingYuName) {
        this.lingYuName = lingYuName;
    }

    public Date getEtlDate() {
        return etlDate;
    }

    public void setEtlDate(Date etlDate) {
        this.etlDate = etlDate;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

}
