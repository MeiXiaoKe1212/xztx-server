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
import java.util.List;

@Schema(description = "用户 APP - 招聘者注册请求 VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppRecruiterRegistrationRequestVO {

    @Schema(description = "手机号码", requiredMode = Schema.RequiredMode.REQUIRED, example = "13277778888")
    @NotEmpty(message = "手机号码不能为空")
    @Mobile
    private String phone;

    @Schema(description = "密码", requiredMode = Schema.RequiredMode.REQUIRED, example = "StrongPass123")
    @NotEmpty(message = "密码不能为空")
    @Length(min = 8, max = 20, message = "密码长度为 8-20 位")
    private String password;

    @Schema(description = "昵称", requiredMode = Schema.RequiredMode.REQUIRED, example = "Recruiter123")
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @Schema(description = "验证码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @NotEmpty(message = "验证码不能为空")
    @Length(min = 4, max = 6, message = "验证码长度为 4-6 位")
    @Pattern(regexp = "^[0-9]+$", message = "验证码必须都是数字")
    private String code;

    @Schema(description = "真实姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    private String name;

    @Schema(description = "性别", requiredMode = Schema.RequiredMode.REQUIRED, example = "男")
    private Integer sex;

    @Schema(description = "用户类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer userType;

    @Schema(description = "头像", requiredMode = Schema.RequiredMode.REQUIRED, example = "avatar_url")
    private String avatar;

    @Schema(description = "公司名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "ABC 公司")
    private String companyName;

    @Schema(description = "担任职位", requiredMode = Schema.RequiredMode.REQUIRED, example = "人力资源经理")
    private String jobTitle;

    @Schema(description = "公司地址", requiredMode = Schema.RequiredMode.REQUIRED, example = "上海市黄浦区")
    private List<Integer> companyAddress;

    @Schema(description = "公司规模", requiredMode = Schema.RequiredMode.REQUIRED, example = "大型企业")
    private String companyScale;

}

