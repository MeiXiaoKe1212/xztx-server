package cn.iocoder.yudao.module.member.controller.app.user.vo;

import cn.iocoder.yudao.framework.common.validation.Mobile;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Schema(description = "用户 APP - 注册 Request VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppMemberUserRegisterReqVO {

    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED, example = "13277778888")
    @NotEmpty(message = "手机号不能为空")
    @Mobile
    private String phone;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "Abc123456")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 4, max = 16, message = "密码长度为 4-16 位")
    private String password;

    @Schema(description = "昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "小黑")
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @Schema(description = "验证码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotEmpty(message = "证码不能为空")
    @Length(min = 4, max = 6, message = "验证码长度为 4-6 位")
    @Pattern(regexp = "^[0-9]+$", message = "验证码必须都是数字")
    private String code;

    @Schema(description = "真实姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "小黑")
    private String name;

    @Schema(description = "性别", requiredMode = Schema.RequiredMode.REQUIRED, example = "男")
    private Integer sex;

    @Schema(description = "用户类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer userType;

    @Schema(description = "头像", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private String avatar;

}
