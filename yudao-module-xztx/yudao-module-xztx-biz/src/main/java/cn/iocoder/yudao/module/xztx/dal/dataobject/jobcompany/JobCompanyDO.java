package cn.iocoder.yudao.module.xztx.dal.dataobject.jobcompany;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 招聘岗位_企业 关联 DO
 *
 * @author 芋道源码
 */
@TableName("xztx_job_company")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobCompanyDO extends BaseDO {

    /**
     * 自增编号
     */
    @TableId
    private Long id;
    /**
     * 岗位ID
     */
    private Long jobId;
    /**
     * 企业ID
     */
    private Long companyId;

}