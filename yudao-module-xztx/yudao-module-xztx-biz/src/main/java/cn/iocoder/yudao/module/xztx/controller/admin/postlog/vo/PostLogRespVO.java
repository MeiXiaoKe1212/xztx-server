package cn.iocoder.yudao.module.xztx.controller.admin.postlog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 投递记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PostLogRespVO {

    @Schema(description = "投递者简历id", example = "13746")
    @ExcelProperty("投递者简历id")
    private Long resumeId;

    @Schema(description = "投递状态（1：已投递，2：已查看，3：已回复）", example = "1")
    @ExcelProperty("投递状态（1：已投递，2：已查看，3：已回复）")
    private Integer status;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "发布者id")
    @ExcelProperty("发布者id")
    private Long createBy;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}