package cn.iocoder.yudao.module.xztx.service.jobcompany;

import cn.iocoder.yudao.module.xztx.dal.dataobject.jobcompany.JobCompanyDO;
import cn.iocoder.yudao.module.xztx.dal.mysql.jobcompany.JobCompanyMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;


/**
 * 招聘岗位_企业 关联 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class JobCompanyServiceImpl extends ServiceImpl<JobCompanyMapper, JobCompanyDO> implements JobCompanyService {

    @Resource
    private JobCompanyMapper jobCompanyMapper;

    @Override
    public Long createJobCompany(JobCompanyDO jobCompany) {

        jobCompanyMapper.insert(jobCompany);
        // 返回
        return jobCompany.getId();
    }

    @Override
    public void updateJobCompany(JobCompanyDO jobCompany) {
        jobCompanyMapper.updateById(jobCompany);
    }

    @Override
    public void deleteJobCompany(Long id) {
        // 删除
        jobCompanyMapper.deleteById(id);
    }


    @Override
    public JobCompanyDO getJobCompany(Long id) {
        return jobCompanyMapper.selectById(id);
    }


}