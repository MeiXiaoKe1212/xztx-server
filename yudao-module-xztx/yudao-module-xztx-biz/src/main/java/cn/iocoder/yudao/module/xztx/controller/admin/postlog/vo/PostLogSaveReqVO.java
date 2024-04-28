package cn.iocoder.yudao.module.xztx.controller.admin.postlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "管理后台 - 投递记录新增/修改 Request VO")
@Data
public class PostLogSaveReqVO {

    @Schema(description = "投递岗位id", example = "1")
    private Long jobId;

}