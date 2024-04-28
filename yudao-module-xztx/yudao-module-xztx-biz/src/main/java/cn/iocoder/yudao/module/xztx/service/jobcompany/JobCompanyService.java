package cn.iocoder.yudao.module.xztx.service.jobcompany;

import cn.iocoder.yudao.module.xztx.dal.dataobject.jobcompany.JobCompanyDO;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 招聘岗位_企业 关联 Service 接口
 *
 * @author 芋道源码
 */
public interface JobCompanyService extends IService<JobCompanyDO> {

    /**
     * 创建招聘岗位_企业 关联
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createJobCompany(JobCompanyDO createReqVO);

    /**
     * 更新招聘岗位_企业 关联
     *
     * @param updateReqVO 更新信息
     */
    void updateJobCompany(JobCompanyDO updateReqVO);

    /**
     * 删除招聘岗位_企业 关联
     *
     * @param id 编号
     */
    void deleteJobCompany(Long id);

    /**
     * 获得招聘岗位_企业 关联
     *
     * @param id 编号
     * @return 招聘岗位_企业 关联
     */
    JobCompanyDO getJobCompany(Long id);

}