package cn.iocoder.yudao.module.xztx.dal.dataobject.company;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 携智同行 企业 DO
 *
 * @author 芋道源码
 */
@TableName("xztx_company")
@KeySequence("xztx_company_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XztxCompanyDO extends BaseDO {

    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 企业名称
     */
    private String name;
    /**
     * 联系人手机号
     */
    private String contactPhone;
    /**
     * 联系人姓名
     */
    private String contactPerson;
    /**
     * 联系人邮箱
     */
    private String contactEmail;
    /**
     * 企业地址
     */
    private String location;
    /**
     * 企业规模
     */
    private String scale;
    /**
     * 平台user_id
     */
    private Long userId;
    /**
     * 状态（0停用，1正常）
     */
    private Integer status;

}