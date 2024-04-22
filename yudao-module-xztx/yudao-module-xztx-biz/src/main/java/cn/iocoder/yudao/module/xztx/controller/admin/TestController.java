package cn.iocoder.yudao.module.xztx.controller.admin;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "管理后台 - Test")
@RestController
@RequestMapping("/xztx/text")
@Validated
public class TestController {


    @GetMapping("/get")
    @Operation(summary = "获得 test 信息")
    public CommonResult<String> get() {
        return CommonResult.success("芋道源码");
    }

}
