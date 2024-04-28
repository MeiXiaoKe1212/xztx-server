package cn.iocoder.yudao.module.xztx.controller.app.postlog;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.annotations.PreAuthenticated;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.xztx.controller.app.vo.AppPostLogFullVO;
import cn.iocoder.yudao.module.xztx.service.postlog.PostLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Tag(name = "用户 App - 投递记录")
@RestController
@RequestMapping("/xztx/postlog")
@Validated
public class AppPostLogController {

    @Resource
    private PostLogService postLogService;

    @PostMapping("/create/{jobId}")
    @PreAuthenticated
    public CommonResult<Long> createPostLog(@PathVariable Long jobId) {
        return CommonResult.success(postLogService.createPostLog(jobId));
    }

    @GetMapping("/hasPosted/{jobId}")
    @PreAuthenticated
    public CommonResult<Boolean> hasPosted(@PathVariable Long jobId) {
        return CommonResult.success(postLogService.hasPosted(jobId, SecurityFrameworkUtils.getLoginUserId()));
    }

    @GetMapping("/list")
    @PreAuthenticated
    public CommonResult<List<AppPostLogFullVO>> getPostLogList() {
        return CommonResult.success(postLogService.getPostLogList());
    }
}
