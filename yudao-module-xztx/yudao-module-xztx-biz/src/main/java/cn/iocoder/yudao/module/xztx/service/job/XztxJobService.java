package cn.iocoder.yudao.module.xztx.service.job;


import cn.iocoder.yudao.module.xztx.controller.admin.job.vo.*;
import cn.iocoder.yudao.module.xztx.controller.app.vo.AppJobSaveReqVO;
import cn.iocoder.yudao.module.xztx.dal.dataobject.job.XztxJobDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.validation.Valid;
import java.util.List;

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
     * App端 新增岗位
     *
     * @param createReqVO AppJobSaveReqVO
     * @return 编号
     */
    Long appCreatJob(@Valid AppJobSaveReqVO createReqVO);


    /**
     * 查看我发布的岗位
     *
     * @return List<XztxJobDO>
     */
    List<XztxJobDO> listByUserId();

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

    /**
     * 处理字典数据
     *
     * @param jobDO
     * @return
     */
    XztxJobDO processDictType(XztxJobDO jobDO);

}