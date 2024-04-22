package cn.iocoder.yudao.module.xztx.controller.admin.job.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.math.BigDecimal;
import java.util.List;

import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 招聘岗位 Response VO")
@Data
@ExcelIgnoreUnannotated
public class JobRespVO {

    @Schema(description = "岗位名称")
    @ExcelProperty("岗位名称")
    private String jobName;

    @Schema(description = "岗位薪资")
    @ExcelProperty("岗位薪资")
    private BigDecimal jobSalary;

    @Schema(description = "岗位描述")
    @ExcelProperty("岗位描述")
    private String jobDescription;

    @Schema(description = "岗位标签")
    @ExcelProperty("岗位标签")
    private String jobLabelStr;

    @Schema(description = "学历 字典值")
    @ExcelProperty("学历 字典值")
    private String eduValue;

    @Schema(description = "工作经验")
    @ExcelProperty("工作经验")
    private String exp;

    @Schema(description = "残疾类别字符串")
    @ExcelProperty("残疾类别字符串")
    private String disTypeStr;

    @Schema(description = "岗位状态", example = "2")
    @ExcelProperty("岗位状态")
    private String jobStatus;

    @Schema(description = "工作福利列表")
    @ExcelProperty("工作福利")
    private List<String> jobWelfareLabels;


    /**
     * 岗位特色
     */
    private List<String> jobTeseLabels;

    /**
     * 岗位行业label
     */
    private String jobIndustryTypeLabel;

    /**
     * 省
     */
    private String provinceName;

    /**
     * 市
     */
    private String cityName;

    /**
     * 县/区
     */
    private String threeCityName;

    /**
     * 学历
     */
    private String eduLabel;

    /**
     * 工作经验
     */
    private String expLabel;

    /**
     * 残疾类别
     */
    private List<String> disTypeLabels;

    /**
     * 工作类型
     */
    private String jobTypeLabel;

}