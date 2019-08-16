package com.dubbo.nacos.api.entity.auth;


import com.dubbo.nacos.api.entity.DnBaseEntity;
import lombok.Data;

/**
 * Permission
 *
 * @author 胡桃夹子
 * @date 2019-07-31 20:18
 */
@Data
public class DnPermission extends DnBaseEntity {

    private static final long serialVersionUID = -7141829387338999544L;

    /**
     * PK, length is 19
     **/
    private Long id;

    /**
     * permission name
     **/
    private String name;

    /**
     * permission code
     **/
    private String code;

    /**
     * permission url
     **/
    private String url;

    /**
     * permission lev
     **/
    private Integer lev;

    /**
     * permission sort
     **/
    private Integer sort;

    /**
     * parent permission pK, length is 19
     **/
    private String parentId;

    /**
     * site code
     **/
    private String siteCode;

    /**
     * remark
     **/
    private String remark;

    public DnPermission() {
        super();
    }

    public DnPermission(Long id, String name, String code, String url, Integer lev, Integer sort, String parentId, String siteCode, String remark) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.url = url;
        this.lev = lev;
        this.sort = sort;
        this.parentId = parentId;
        this.siteCode = siteCode;
        this.remark = remark;
    }
}
