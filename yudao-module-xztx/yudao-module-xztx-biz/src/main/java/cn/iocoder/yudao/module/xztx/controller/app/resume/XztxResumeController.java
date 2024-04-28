package cn.iocoder.yudao.module.xztx.controller.app.resume;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.security.core.annotations.PreAuthenticated;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.xztx.controller.app.vo.AppResumeUploadReqVO;
import cn.iocoder.yudao.module.xztx.dal.dataobject.resume.XztxResumeDO;
import cn.iocoder.yudao.module.xztx.service.resume.XztxResumeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/xztx/resume")
public class XztxResumeController {

    @Resource
    private XztxResumeService resumeService;

    @PostMapping("/upload")
    @PreAuthenticated
    public CommonResult<String> create(AppResumeUploadReqVO vo) throws Exception {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        long count = resumeService.count(new LambdaQueryWrapper<XztxResumeDO>().eq(XztxResumeDO::getCreator, userId));
        if (count >= 3) {
            return CommonResult.error(500, "附件简历不得超过3份");
        }
        return CommonResult.success(resumeService.uploadResume(vo));
    }

    @GetMapping("/list")
    @PreAuthenticated
    public CommonResult<List<XztxResumeDO>> list() {
        Long userId = SecurityFrameworkUtils.getLoginUserId();
        return CommonResult.success(resumeService.list(new LambdaQueryWrapper<XztxResumeDO>().eq(XztxResumeDO::getCreator, userId).orderByDesc(XztxResumeDO::getCreateTime)));
    }

    @PutMapping("/update")
    @PreAuthenticated
    public CommonResult<String> update(@RequestBody XztxResumeDO resumeDO) {
        if (resumeService.updateById(resumeDO)) {
            return CommonResult.success("更新成功");
        }
        return CommonResult.error(500, "更新失败");
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthenticated
    public CommonResult<String> delete(@PathVariable("id") Long id) {
        if (resumeService.removeById(id)) {
            return CommonResult.success("删除成功");
        }
        return CommonResult.error(500, "删除失败");
    }

}
