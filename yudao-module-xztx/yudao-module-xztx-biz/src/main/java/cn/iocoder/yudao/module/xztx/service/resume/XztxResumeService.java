package cn.iocoder.yudao.module.xztx.service.resume;

import cn.iocoder.yudao.module.xztx.controller.app.vo.AppResumeUploadReqVO;
import cn.iocoder.yudao.module.xztx.dal.dataobject.resume.XztxResumeDO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface XztxResumeService extends IService<XztxResumeDO> {

    boolean createResume(XztxResumeDO resumeDO);

    String uploadResume(AppResumeUploadReqVO vo) throws Exception;

}
