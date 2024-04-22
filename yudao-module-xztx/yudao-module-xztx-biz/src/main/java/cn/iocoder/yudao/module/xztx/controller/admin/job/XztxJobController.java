package cn.iocoder.yudao.module.xztx.controller.admin.job;

import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.xztx.controller.admin.job.vo.*;
import cn.iocoder.yudao.module.xztx.dal.dataobject.job.XztxJobDO;
import cn.iocoder.yudao.module.xztx.service.job.XztxJobService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Tag(name = "管理后台 - 招聘岗位")
@RestController
@RequestMapping("/xztx/job")
@Validated
public class XztxJobController {

    @Resource
    private XztxJobService xztxJobService;

    @PostMapping("/create")
    @Operation(summary = "创建招聘岗位")
    @PreAuthorize("@ss.hasPermission('xztx:job:create')")
    public CommonResult<Long> createJob(@Valid @RequestBody JobSaveReqVO createReqVO) {
        return success(xztxJobService.createJob(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新招聘岗位")
    @PreAuthorize("@ss.hasPermission('xztx:job:update')")
    public CommonResult<Boolean> updateJob(@Valid @RequestBody JobSaveReqVO updateReqVO) {
        xztxJobService.updateJob(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除招聘岗位")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('xztx:job:delete')")
    public CommonResult<Boolean> deleteJob(@RequestParam("id") Long id) {
        xztxJobService.deleteJob(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得招聘岗位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('xztx:job:query')")
    public CommonResult<JobRespVO> getJob(@RequestParam("id") Long id) {
        XztxJobDO job = xztxJobService.getJob(id);
        return success(BeanUtils.toBean(job, JobRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得招聘岗位分页")
    @PreAuthorize("@ss.hasPermission('xztx:job:query')")
    public CommonResult<PageResult<JobRespVO>> getJobPage(@Valid JobPageReqVO pageReqVO) {
        PageResult<XztxJobDO> pageResult = xztxJobService.getJobPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, JobRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出招聘岗位 Excel")
    @PreAuthorize("@ss.hasPermission('xztx:job:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportJobExcel(@Valid JobPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<XztxJobDO> list = xztxJobService.getJobPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "招聘岗位.xls", "数据", JobRespVO.class,
                        BeanUtils.toBean(list, JobRespVO.class));
    }

}