package cn.iocoder.yudao.module.xztx.dal.dataobject.job;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 招聘岗位 DO
 *
 * @author 芋道源码
 */
@TableName("xztx_job")
@KeySequence("xztx_job_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class XztxJobDO extends BaseDO {

    /**
     * 主键id
     */
    @TableId
    private Long id;
    /**
     * 企业id
     */
    private Long companyId;
    /**
     * 岗位名称
     */
    private String jobName;
    /**
     * 岗位起始薪资
     */
    private BigDecimal jobMinSalary;
    /**
     * 岗位最高薪资
     */
    private BigDecimal jobMaxSalary;
    /**
     * 岗位描述
     */
    private String jobDescription;
    /**
     * 岗位要求
     */
    private String jobRequirement;
    /**
     * 岗位福利id字符串
     */
    private String jobWelfareIds;
    /**
     * 岗位其它信息
     */
    private String jobOtherInfo;
    /**
     * 岗位特色id字符串
     */
    private String jobTeseIds;
    /**
     * 工作类型（例：1全职、2兼职、3实习）
     */
    private Integer jobType;
    /**
     * 行业
     */
    private Integer jobIndustryType;
    /**
     * 省 id
     */
    private String provinceId;
    /**
     * 市 id
     */
    private String cityId;
    /**
     * 县/区 id
     */
    private String threeCityId;
    /**
     * 详细地址
     */
    private String jobAddressDetail;
    /**
     * 是否招聘应届生
     */
    private char isCollegeStudent;
    /**
     * 学历
     */
    private String edu;
    /**
     * 工作经验
     */
    private String exp;
    /**
     * 残疾类别id字符串
     */
    private String disTypeIds;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 专业
     */
    private String major;
    /**
     * 岗位状态
     */
    private String jobStatus;
    /**
     * 招聘开始时间
     */
    private Date beginTime;
    /**
     * 招聘结束时间
     */
    private Date endTime;
    /**
     * 发布者id
     */
    private Long createBy;

}