package cn.iocoder.yudao.module.xztx.service.job;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.dict.core.DictFrameworkUtils;
import cn.iocoder.yudao.framework.ip.core.Area;
import cn.iocoder.yudao.framework.ip.core.utils.AreaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.xztx.controller.admin.job.vo.*;
import cn.iocoder.yudao.module.xztx.dal.dataobject.job.XztxJobDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.xztx.dal.mysql.job.XztxJobMapper;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.xztx.enums.ErrorCodeConstants.*;

/**
 * 招聘岗位 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
@Slf4j
public class XztxJobServiceImpl implements XztxJobService {

    @Resource
    private XztxJobMapper xztxJobMapper;

    @Override
    public Long createJob(JobSaveReqVO createReqVO) {
        log.debug(createReqVO.toString());
        // 插入
        XztxJobDO job = BeanUtils.toBean(createReqVO, XztxJobDO.class);
        String jobBeginEndDate = createReqVO.getJobBeginEndDate();
        String[] split = jobBeginEndDate.split(",");
        job.setBeginTime(DateUtil.parse(split[0]));
        job.setEndTime(DateUtil.parse(split[1]));
        xztxJobMapper.insert(job);
        return job.getId();
    }

    @Override
    public void updateJob(JobSaveReqVO updateReqVO) {
        // 校验存在
//        validateJobExists(updateReqVO.getId());
        // 更新
        XztxJobDO updateObj = BeanUtils.toBean(updateReqVO, XztxJobDO.class);
        xztxJobMapper.updateById(updateObj);
    }

    @Override
    public void deleteJob(Long id) {
        // 校验存在
        validateJobExists(id);
        // 删除
        xztxJobMapper.deleteById(id);
    }

    private void validateJobExists(Long id) {
        if (xztxJobMapper.selectById(id) == null) {
            throw exception(JOB_NOT_EXISTS);
        }
    }

    @Override
    public XztxJobDO getJob(Long id) {
        return xztxJobMapper.selectById(id);
    }

    @Override
    public PageResult<XztxJobDO> getJobPage(JobPageReqVO pageReqVO) {
        return xztxJobMapper.selectPage(pageReqVO);
    }

    @Override
    public XztxJobDO processDictType(XztxJobDO jobDO) {
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
        return jobDO;
    }

}