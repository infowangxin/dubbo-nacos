package com.dubbo.nacos.api.entity;

import java.io.Serializable;

/**
 * @author 王鑫
 * @date 2019-07-28 10:43
 */
public interface BaseEntity<Pk extends Serializable> extends Serializable {

    public void setId(Pk id);

    public Pk getId();

}
