package cn.iocoder.yudao.module.xztx.controller.admin.company;

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

import cn.iocoder.yudao.module.xztx.controller.admin.company.vo.*;
import cn.iocoder.yudao.module.xztx.dal.dataobject.company.XztxCompanyDO;
import cn.iocoder.yudao.module.xztx.service.company.XztxCompanyService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Tag(name = "管理后台 - 携智同行 企业")
@RestController
@RequestMapping("/xztx/company")
@Validated
public class XztxCompanyController {

    @Resource
    private XztxCompanyService companyService;

    @PostMapping("/create")
    @Operation(summary = "创建携智同行 企业")
    @PreAuthorize("@ss.hasPermission('xztx:company:create')")
    public CommonResult<Long> createCompany(@Valid @RequestBody XztxCompanySaveReqVO createReqVO) {
        return success(companyService.createCompany(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新携智同行 企业")
    @PreAuthorize("@ss.hasPermission('xztx:company:update')")
    public CommonResult<Boolean> updateCompany(@Valid @RequestBody XztxCompanySaveReqVO updateReqVO) {
        companyService.updateCompany(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除携智同行 企业")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('xztx:company:delete')")
    public CommonResult<Boolean> deleteCompany(@RequestParam("id") Long id) {
        companyService.deleteCompany(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得携智同行 企业")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('xztx:company:query')")
    public CommonResult<XztxCompanyRespVO> getCompany(@RequestParam("id") Long id) {
        XztxCompanyDO company = companyService.getCompany(id);
        return success(BeanUtils.toBean(company, XztxCompanyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得携智同行 企业分页")
    @PreAuthorize("@ss.hasPermission('xztx:company:query')")
    public CommonResult<PageResult<XztxCompanyRespVO>> getCompanyPage(@Valid XztxCompanyPageReqVO pageReqVO) {
        PageResult<XztxCompanyDO> pageResult = companyService.getCompanyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, XztxCompanyRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出携智同行 企业 Excel")
    @PreAuthorize("@ss.hasPermission('xztx:company:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCompanyExcel(@Valid XztxCompanyPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<XztxCompanyDO> list = companyService.getCompanyPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "携智同行 企业.xls", "数据", XztxCompanyRespVO.class,
                        BeanUtils.toBean(list, XztxCompanyRespVO.class));
    }

}