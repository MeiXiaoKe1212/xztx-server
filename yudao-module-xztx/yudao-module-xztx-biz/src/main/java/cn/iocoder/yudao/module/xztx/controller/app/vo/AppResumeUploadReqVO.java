package cn.iocoder.yudao.module.xztx.controller.app.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Data
public class AppResumeUploadReqVO {

    @Schema(description = "文件附件", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "文件附件不能为空")
    private MultipartFile file;

    @Schema(description = "文件附件", example = "yudaoyuanma.png")
    private String path;

    private String name;

    /**
     * 文件大小 单位字节
     */
    private Long size;

}
