package cn.iocoder.yudao.module.xztx.controller.admin.company.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Schema(description = "管理后台 - 携智同行 企业新增/修改 Request VO")
@Data
public class XztxCompanySaveReqVO {

    @Schema(description = "企业名称")
    private String name;

    @Schema(description = "联系人手机号")
    private String contactPhone;

    @Schema(description = "联系人姓名")
    private String contactPerson;

    @Schema(description = "联系人邮箱")
    private String contactEmail;

    @Schema(description = "企业地址")
    private List<Integer> locationList;

    @Schema(description = "企业规模")
    private String scale;

    @Schema(description = "状态（0停用，1正常）", example = "1")
    private Integer status;

    @Schema(description = "创建者")
    private Long userId;

}