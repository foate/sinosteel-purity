package com.sinosteel.domain.dictionary;

/**
 * 企业对象模型
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sinosteel.domain.BaseEntity;
import com.sinosteel.domain.LegalPerson;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "zj_dict_common")
public class ZjDictCommon extends BaseEntity {

    @Column(name = "DICTYPE")
    @NotEmpty
    private String dicType; //字典类型
    @Column(name = "VERSION")
    private Long version; //
    @Column(name = "DICNAME")
    private String dicName; //字典名称
    @Column(name = "ORDERID")
    private Long orderId; //
    @Column(name = "CODE")
    @NotEmpty
    private String code; //字典编码
    @Column(name = "CAPTION")
    private String caption; //标题
    @Column(name = "EXPLAIN")
    private String explain; //解释
    @Column(name = "ISDELETE")
    private String isDelete; // 是否删除  0：未删除 1：已删除
    @Column(name = "REMARK")
    private String remark; // 备注
    @Column(name = "SORTID")
    private String sortId; //

    public String getDicType() {
        return dicType;
    }

    public void setDicType(String dicType) {
        this.dicType = dicType;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getDicName() {
        return dicName;
    }

    public void setDicName(String dicName) {
        this.dicName = dicName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }
}
