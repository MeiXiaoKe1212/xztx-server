package cn.iocoder.yudao.module.xztx.controller.admin.company.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 携智同行 企业 Response VO")
@Data
@ExcelIgnoreUnannotated
public class XztxCompanyRespVO {

    @Schema(description = "企业名称")
    @ExcelProperty("企业名称")
    private String name;

    @Schema(description = "联系人姓名")
    @ExcelProperty("联系人姓名")
    private String contactPerson;

    @Schema(description = "企业地址")
    @ExcelProperty("企业地址")
    private String location;

    @Schema(description = "企业规模")
    @ExcelProperty("企业规模")
    private String scale;

    @Schema(description = "状态（0停用，1正常）", example = "2")
    @ExcelProperty("状态（0停用，1正常）")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}