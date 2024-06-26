package cn.iocoder.yudao.module.xztx.controller.admin.job;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.util.string.StrUtils;
import cn.iocoder.yudao.framework.dict.core.DictFrameworkUtils;
import cn.iocoder.yudao.framework.ip.core.Area;
import cn.iocoder.yudao.framework.ip.core.utils.AreaUtils;
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
    public CommonResult<XztxJobDO> getJob(@RequestParam("id") Long id) {
        XztxJobDO jobDO = xztxJobService.getJob(id);
        // region 对字典值处理
        // 1. 工作福利
        String jobWelfareIds = jobDO.getJobWelfareIds();
        List<String> jobWelfareLabels = new ArrayList<>();
        if (!StrUtil.hasEmpty(jobWelfareIds)) {
            String[] welfareIds = jobWelfareIds.split(",");
            for (String welfareId : welfareIds) {
                String jobWelfare = DictFrameworkUtils.getDictDataLabel("job_welfare", welfareId);
                if (StrUtil.hasEmpty(jobWelfare)) continue;
                jobWelfareLabels.add(jobWelfare);
            }
        }
        jobDO.setJobWelfareLabels(jobWelfareLabels);
        // 2. 岗位特色
        String jobTeseIds = jobDO.getJobTeseIds();
        List<String> jobTeseLabels = new ArrayList<>();
        if (!StrUtil.hasEmpty(jobTeseIds)) {
            String[] teseIds = jobTeseIds.split(",");
            for (String teseId : teseIds) {
                String jobTese = DictFrameworkUtils.getDictDataLabel("job_tese", teseId);
                if (StrUtil.hasEmpty(jobTese)) continue;
                jobTeseLabels.add(jobTese);
            }
        }
        jobDO.setJobTeseLabels(jobTeseLabels);
        // 3. 岗位行业
        Integer jobIndustryType = jobDO.getJobIndustryType();
        String jobIndustryTypeLabel = DictFrameworkUtils.getDictDataLabel("job_industry_type", jobIndustryType);
        if (!StrUtil.hasEmpty(jobIndustryTypeLabel)) {
            jobDO.setJobIndustryTypeLabel(jobIndustryTypeLabel);
        }
        // 4. 省、市、区
        String provinceId = jobDO.getProvinceId();
        String cityId = jobDO.getCityId();
        String threeCityId = jobDO.getThreeCityId();
        if (!StrUtil.hasEmpty(provinceId)) {
            Area area = AreaUtils.getArea(Integer.valueOf(provinceId));
            if (!Objects.isNull(area)) {
                jobDO.setProvinceName(area.getName());
            }
        }
        if (!StrUtil.hasEmpty(cityId)) {
            Area area = AreaUtils.getArea(Integer.valueOf(cityId));
            if (!Objects.isNull(area)) {
                jobDO.setCityName(area.getName());
            }
        }
        if (!StrUtil.hasEmpty(threeCityId)) {
            Area area = AreaUtils.getArea(Integer.valueOf(threeCityId));
            if (!Objects.isNull(area)) {
                jobDO.setThreeCityName(area.getName());
            }
        }
        // 5. 学历
        String eduId = jobDO.getEdu();
        String eduLabel = DictFrameworkUtils.getDictDataLabel("user_edu", eduId);
        jobDO.setEduLabel(eduLabel);
        // 6. 工作经验
        String expId = jobDO.getExp();
        String expLabel = DictFrameworkUtils.getDictDataLabel("user_exp", expId);
        jobDO.setExpLabel(expLabel);
        // 7. 残疾类别
        String disTypeIds = jobDO.getDisTypeIds();
        List<String> disTypeLabels = new ArrayList<>();
        if (!StrUtil.hasEmpty(disTypeIds)) {
            String[] typeIds = disTypeIds.split(",");
            for (String typeId : typeIds) {
                String disabledType = DictFrameworkUtils.getDictDataLabel("disabled_type", typeId);
                if (StrUtil.hasEmpty(disabledType)) continue;
                disTypeLabels.add(disabledType);
            }
        }
        jobDO.setDisTypeLabels(disTypeLabels);
        // 8. 工作类型
        Integer jobType = jobDO.getJobType();
        String jobTypeLabel = DictFrameworkUtils.getDictDataLabel("job_type", jobType);
        jobDO.setJobTypeLabel(jobTypeLabel);
        // endregion
        return success(jobDO);
    }

    @GetMapping("/page")
    @Operation(summary = "获得招聘岗位分页")
    @PreAuthorize("@ss.hasPermission('xztx:job:query')")
    public CommonResult<PageResult<XztxJobDO>> getJobPage(@Valid JobPageReqVO pageReqVO) {
        PageResult<XztxJobDO> pageResult = xztxJobService.getJobPage(pageReqVO);
        List<XztxJobDO> list = pageResult.getList();
        for (XztxJobDO jobDO : list) {
            // region 对字典值处理
            // 1. 工作福利
            String jobWelfareIds = jobDO.getJobWelfareIds();
            List<String> jobWelfareLabels = new ArrayList<>();
            if (!StrUtil.hasEmpty(jobWelfareIds)) {
                String[] welfareIds = jobWelfareIds.split(",");
                for (String welfareId : welfareIds) {
                    String jobWelfare = DictFrameworkUtils.getDictDataLabel("job_welfare", welfareId);
                    if (StrUtil.hasEmpty(jobWelfare)) continue;
                    jobWelfareLabels.add(jobWelfare);
                }
            }
            jobDO.setJobWelfareLabels(jobWelfareLabels);
            // 2. 岗位特色
            String jobTeseIds = jobDO.getJobTeseIds();
            List<String> jobTeseLabels = new ArrayList<>();
            if (!StrUtil.hasEmpty(jobTeseIds)) {
                String[] teseIds = jobTeseIds.split(",");
                for (String teseId : teseIds) {
                    String jobTese = DictFrameworkUtils.getDictDataLabel("job_tese", teseId);
                    if (StrUtil.hasEmpty(jobTese)) continue;
                    jobTeseLabels.add(jobTese);
                }
            }
            jobDO.setJobTeseLabels(jobTeseLabels);
            // 3. 岗位行业
            Integer jobIndustryType = jobDO.getJobIndustryType();
            String jobIndustryTypeLabel = DictFrameworkUtils.getDictDataLabel("job_industry_type", jobIndustryType);
            if (!StrUtil.hasEmpty(jobIndustryTypeLabel)) {
                jobDO.setJobIndustryTypeLabel(jobIndustryTypeLabel);
            }
            // 4. 省、市、区
            String provinceId = jobDO.getProvinceId();
            String cityId = jobDO.getCityId();
            String threeCityId = jobDO.getThreeCityId();
            if (!StrUtil.hasEmpty(provinceId)) {
                Area area = AreaUtils.getArea(Integer.valueOf(provinceId));
                if (!Objects.isNull(area)) {
                    jobDO.setProvinceName(area.getName());
                }
            }
            if (!StrUtil.hasEmpty(cityId)) {
                Area area = AreaUtils.getArea(Integer.valueOf(cityId));
                if (!Objects.isNull(area)) {
                    jobDO.setCityName(area.getName());
                }
            }
            if (!StrUtil.hasEmpty(threeCityId)) {
                Area area = AreaUtils.getArea(Integer.valueOf(threeCityId));
                if (!Objects.isNull(area)) {
                    jobDO.setThreeCityName(area.getName());
                }
            }
            // 5. 学历
            String eduId = jobDO.getEdu();
            String eduLabel = DictFrameworkUtils.getDictDataLabel("user_edu", eduId);
            jobDO.setEduLabel(eduLabel);
            // 6. 工作经验
            String expId = jobDO.getExp();
            String expLabel = DictFrameworkUtils.getDictDataLabel("user_exp", expId);
            jobDO.setExpLabel(expLabel);
            // 7. 残疾类别
            String disTypeIds = jobDO.getDisTypeIds();
            List<String> disTypeLabels = new ArrayList<>();
            if (!StrUtil.hasEmpty(disTypeIds)) {
                String[] typeIds = disTypeIds.split(",");
                for (String id : typeIds) {
                    String disabledType = DictFrameworkUtils.getDictDataLabel("disabled_type", id);
                    if (StrUtil.hasEmpty(disabledType)) continue;
                    disTypeLabels.add(disabledType);
                }
            }
            jobDO.setDisTypeLabels(disTypeLabels);
            // 8. 工作类型
            Integer jobType = jobDO.getJobType();
            String jobTypeLabel = DictFrameworkUtils.getDictDataLabel("job_type", jobType);
            jobDO.setJobTypeLabel(jobTypeLabel);
            // endregion
        }

        return success(pageResult);
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