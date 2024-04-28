package cn.iocoder.yudao.module.xztx.controller.admin.postlog;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.xztx.controller.admin.postlog.vo.PostLogPageReqVO;
import cn.iocoder.yudao.module.xztx.controller.admin.postlog.vo.PostLogRespVO;
import cn.iocoder.yudao.module.xztx.controller.admin.postlog.vo.PostLogSaveReqVO;
import cn.iocoder.yudao.module.xztx.dal.dataobject.postlog.PostLogDO;
import cn.iocoder.yudao.module.xztx.service.postlog.PostLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 投递记录")
@RestController
@RequestMapping("/xztx/post-log")
@Validated
public class PostLogController {

    @Resource
    private PostLogService postLogService;


    @DeleteMapping("/delete")
    @Operation(summary = "删除投递记录")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('xztx:post-log:delete')")
    public CommonResult<Boolean> deletePostLog(@RequestParam("id") Long id) {
        postLogService.deletePostLog(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得投递记录")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('xztx:post-log:query')")
    public CommonResult<PostLogRespVO> getPostLog(@RequestParam("id") Long id) {
        PostLogDO postLog = postLogService.getPostLog(id);
        return success(BeanUtils.toBean(postLog, PostLogRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得投递记录分页")
    @PreAuthorize("@ss.hasPermission('xztx:post-log:query')")
    public CommonResult<PageResult<PostLogRespVO>> getPostLogPage(@Valid PostLogPageReqVO pageReqVO) {
        PageResult<PostLogDO> pageResult = postLogService.getPostLogPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PostLogRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出投递记录 Excel")
    @PreAuthorize("@ss.hasPermission('xztx:post-log:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPostLogExcel(@Valid PostLogPageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PostLogDO> list = postLogService.getPostLogPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "投递记录.xls", "数据", PostLogRespVO.class,
                BeanUtils.toBean(list, PostLogRespVO.class));
    }

}