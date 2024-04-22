package cn.iocoder.yudao.module.xztx.service.job;


import cn.iocoder.yudao.module.xztx.controller.admin.job.vo.*;
import cn.iocoder.yudao.module.xztx.dal.dataobject.job.XztxJobDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.validation.Valid;

/**
 * 招聘岗位 Service 接口
 *
 * @author 芋道源码
 */
public interface XztxJobService {

    /**
     * 创建招聘岗位
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createJob(@Valid JobSaveReqVO createReqVO);

    /**
     * 更新招聘岗位
     *
     * @param updateReqVO 更新信息
     */
    void updateJob(@Valid JobSaveReqVO updateReqVO);

    /**
     * 删除招聘岗位
     *
     * @param id 编号
     */
    void deleteJob(Long id);

    /**
     * 获得招聘岗位
     *
     * @param id 编号
     * @return 招聘岗位
     */
    XztxJobDO getJob(Long id);

    /**
     * 获得招聘岗位分页
     *
     * @param pageReqVO 分页查询
     * @return 招聘岗位分页
     */
    PageResult<XztxJobDO> getJobPage(JobPageReqVO pageReqVO);

}