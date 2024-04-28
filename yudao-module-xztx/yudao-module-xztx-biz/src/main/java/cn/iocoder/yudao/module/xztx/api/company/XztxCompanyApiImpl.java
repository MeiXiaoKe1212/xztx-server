package cn.iocoder.yudao.module.xztx.api.company;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.xztx.api.XztxCompanyApi;
import cn.iocoder.yudao.module.xztx.api.dto.CompanyForCreateDTO;
import cn.iocoder.yudao.module.xztx.controller.admin.company.vo.XztxCompanySaveReqVO;
import cn.iocoder.yudao.module.xztx.dal.dataobject.company.XztxCompanyDO;
import cn.iocoder.yudao.module.xztx.dal.mysql.company.XztxCompanyMapper;
import cn.iocoder.yudao.module.xztx.service.company.XztxCompanyService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class XztxCompanyApiImpl implements XztxCompanyApi {

    @Resource
    private XztxCompanyMapper companyMapper;

    @Resource
    private XztxCompanyService xztxCompanyService;

    @Override
    public boolean existAccount(String phone) {
        return companyMapper.exists(new LambdaQueryWrapper<XztxCompanyDO>().eq(XztxCompanyDO::getContactPhone, phone));
    }

    @Override
    public Long createCompany(CompanyForCreateDTO dto) {
        XztxCompanySaveReqVO companyDO = BeanUtils.toBean(dto, XztxCompanySaveReqVO.class);
        return xztxCompanyService.createCompany(companyDO);
    }
}
