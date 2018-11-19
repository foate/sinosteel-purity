package com.sinosteel.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Date;

/**
 *法人对象模型
 */
@Entity
@Table(name = "person")
public class LegalPerson extends BaseEntity {

    @Column(name = "sex")

    private String sex;//性别

    @Column(name = "nation")
    private String nation;//民族

    @Column(name = "persion_id")
    private String persionId;//身份证号

    @Column(name = "birthday")
    private Date birthday;//出生日期

    @Column(name = "education")
    private String education;//学历

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;//所属公司

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

    public String getPersionId() {
        return persionId;
    }

    public void setPersionId(String persionId) {
        this.persionId = persionId;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

}
