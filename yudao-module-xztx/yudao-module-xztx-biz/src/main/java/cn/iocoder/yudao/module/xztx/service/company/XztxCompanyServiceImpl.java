package cn.iocoder.yudao.module.xztx.service.company;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.ip.core.Area;
import cn.iocoder.yudao.framework.ip.core.utils.AreaUtils;
import cn.iocoder.yudao.module.xztx.controller.admin.company.vo.XztxCompanyPageReqVO;
import cn.iocoder.yudao.module.xztx.controller.admin.company.vo.XztxCompanySaveReqVO;
import cn.iocoder.yudao.module.xztx.dal.dataobject.company.XztxCompanyDO;
import cn.iocoder.yudao.module.xztx.dal.mysql.company.XztxCompanyMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.List;

/**
 * 携智同行 企业 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class XztxCompanyServiceImpl implements XztxCompanyService {

    @Resource
    private XztxCompanyMapper companyMapper;

    @Override
    public Long createCompany(XztxCompanySaveReqVO createReqVO) {
        XztxCompanyDO company = BeanUtils.toBean(createReqVO, XztxCompanyDO.class);
        List<Integer> locationList = createReqVO.getLocationList();
        StringBuilder location = new StringBuilder();
        if (locationList != null && !locationList.isEmpty()) {
            for (Integer areaId : locationList) {
                Area area = AreaUtils.getArea(areaId);
                location.append(area.getName());
            }
        }
        company.setLocation(location.toString());
        // 插入
        companyMapper.insert(company);
        // 返回
        return company.getId();
    }

    @Override
    public void updateCompany(XztxCompanySaveReqVO updateReqVO) {
        // 校验存在
//        validateCompanyExists(updateReqVO.getId());
        // 更新
        XztxCompanyDO updateObj = BeanUtils.toBean(updateReqVO, XztxCompanyDO.class);
        companyMapper.updateById(updateObj);
    }

    @Override
    public void deleteCompany(Long id) {
        // 校验存在
        validateCompanyExists(id);
        // 删除
        companyMapper.deleteById(id);
    }

    private void validateCompanyExists(Long id) {
        if (companyMapper.selectById(id) == null) {
//            throw exception(COMPANY_NOT_EXISTS);
        }
    }

    @Override
    public XztxCompanyDO getCompany(Long id) {
        return companyMapper.selectById(id);
    }

    @Override
    public PageResult<XztxCompanyDO> getCompanyPage(XztxCompanyPageReqVO pageReqVO) {
        return companyMapper.selectPage(pageReqVO);
    }

    @Override
    public XztxCompanyDO getCompanyByPhone(String phone) {
        return companyMapper.selectOne("contact_phone", phone);
    }

}