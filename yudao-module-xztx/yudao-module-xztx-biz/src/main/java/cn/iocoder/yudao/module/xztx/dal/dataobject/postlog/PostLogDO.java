package cn.iocoder.yudao.module.xztx.dal.dataobject.postlog;

import lombok.*;

import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 投递记录 DO
 *
 * @author 芋道源码
 */
@TableName("xztx_post_log")
@KeySequence("xztx_post_log_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostLogDO extends BaseDO {

    /**
     * 投递记录id
     */
    @TableId
    private Long id;
    /**
     * 投递岗位id
     */
    private Long jobId;
    /**
     * 投递者id
     */
    private Long applyId;
    /**
     * 岗位联系人id
     */
    private Long contactUserId;
    /**
     * 投递者简历id
     */
    private Long resumeId;
    /**
     * 投递状态（1：已投递，2：已查看，3：已回复）
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 发布者id
     */
    private Long createBy;

}