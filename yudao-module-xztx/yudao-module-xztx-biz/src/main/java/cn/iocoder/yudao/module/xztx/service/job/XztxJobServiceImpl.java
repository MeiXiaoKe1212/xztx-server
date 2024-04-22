package cn.iocoder.yudao.module.xztx.service.job;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.xztx.controller.admin.job.vo.*;
import cn.iocoder.yudao.module.xztx.dal.dataobject.job.XztxJobDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.xztx.dal.mysql.job.XztxJobMapper;

import javax.annotation.Resource;

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

}