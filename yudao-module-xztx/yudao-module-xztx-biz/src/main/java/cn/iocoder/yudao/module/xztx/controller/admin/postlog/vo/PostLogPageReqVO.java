package cn.iocoder.yudao.module.xztx.controller.admin.postlog.vo;

import lombok.*;

import java.util.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 投递记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PostLogPageReqVO extends PageParam {

    @Schema(description = "岗位联系人电话", example = "14025")
    private Long companyUserId;

    @Schema(description = "投递者简历id", example = "13746")
    private Long resumeId;

    @Schema(description = "投递状态（1：已投递，2：已查看，3：已回复）", example = "1")
    private Integer status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "发布者id")
    private Long createBy;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}