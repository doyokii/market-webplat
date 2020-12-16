package com.example.web.entity;



import org.springframework.util.StringUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * @author : Zhuang Jialong
 * @description : 实体基类
 * @date : 2020/11/24 下午 2:07
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
public abstract class CommonEntity implements Serializable {
    protected String uuid;
    protected String createBy;
    protected Date createDate;
    protected String updateBy;
    protected Date updateDate;
    protected String remarks;
    private Object orderBy;

    public Object getOrderBy() {
        return this.orderBy;
    }

    public void setOrderBy(Object orderBy) {
        this.orderBy = orderBy;
    }

    public CommonEntity() {
    }

    public CommonEntity(String id) {
        this.setId(id);
    }

    /** @deprecated */
    @Deprecated
    public String getId() {
        return this.uuid;
    }

    /** @deprecated */
    @Deprecated
    public void setId(String id) {
        this.uuid = id;
    }

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return this.updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void preInsert() {
        if (StringUtils.isEmpty(this.getUuid())) {
            this.setUuid(UUID.randomUUID().toString().trim().replace("-",""));
        }

        Date createDate = new Date();
        this.setCreateDate(createDate);
        this.setUpdateDate(createDate);
    }

    public void preUpdate() {
        this.setUpdateDate(new Date());
    }
}
