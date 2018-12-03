package com.sinosteel.domain.dictionary;

/**
 * 企业对象模型
 */

import com.sinosteel.domain.BaseEntity;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "zj_dict_area")
public class ZjDictArea extends BaseEntity {

    @Column(name = "TYPE")
    private String type; //字典类型
    @Column(name = "TYPENAME")
    private String typeName; //字典类型名称
    @Column(name = "CODE")
    private String code; //字典编码
    @Column(name = "VALUE")
    private String value; //字典编码值
    @Column(name = "XZQHOUID")
    private Long xzqhOuid; //
    @Column(name = "OLDCODE")
    private String oldCode; //

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getXzqhOuid() {
        return xzqhOuid;
    }

    public void setXzqhOuid(Long xzqhOuid) {
        this.xzqhOuid = xzqhOuid;
    }

    public String getOldCode() {
        return oldCode;
    }

    public void setOldCode(String oldCode) {
        this.oldCode = oldCode;
    }
}
