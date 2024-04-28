package cn.iocoder.yudao.module.xztx.dal.dataobject.resume;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 简历信息 Data Object（DO）
 */
@TableName("xztx_resume")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XztxResumeDO extends BaseDO {

    /** 简历ID */
    @TableId
    private Long id;

    /** 简历URL */
    private String url;

    /** 简历名称 */
    private String name;

    /** 简历类型 */
    private String type;

    /** 简历大小 */
    private Long size;

    private char defaultFlag;

}
