package cn.iocoder.yudao.module.xztx.controller.admin.job.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.math.BigDecimal;

@Schema(description = "管理后台 - 招聘岗位新增/修改 Request VO")
@Data
public class JobSaveReqVO {

    @Schema(description = "岗位名称")
    private String jobName;

    @Schema(description = "岗位起始薪资")
    private BigDecimal jobMinSalary;

    @Schema(description = "岗位最高薪资")
    private BigDecimal jobMaxSalary;

    @Schema(description = "岗位描述")
    private String jobDescription;

    @Schema(description = "岗位要求")
    private String jobRequirement;

    @Schema(description = "岗位福利id")
    private String jobWelfareIds;

    @Schema(description = "岗位其它信息")
    private String jobOtherInfo;

    @Schema(description = "岗位特色id字符串")
    private String jobTeseIds;

    @Schema(description = "工作类型（例：1全职、2兼职、3实习）")
    private Integer jobType;

    @Schema(description = "行业")
    private Integer jobIndustryType;

    @Schema(description = "省id")
    private String provinceId;

    @Schema(description = "市id")
    private String cityId;

    @Schema(description = "县/区 id")
    private String threeCityId;

    @Schema(description = "详细地址")
    private String jobAddressDetail;

    @Schema(description = "是否招聘应届生")
    private char isCollegeStudent;

    @Schema(description = "学历")
    private Integer edu;

    @Schema(description = "工作经验")
    private Integer exp;

    @Schema(description = "残疾类别id字符串")
    private String disTypeIds;

    @Schema(description = "年龄")
    private Integer age;

    @Schema(description = "专业")
    private String major;

    @Schema(description = "岗位状态")
    private Integer jobStatus;

    @Schema(description = "招聘起址时间")
    private String jobBeginEndDate;

}