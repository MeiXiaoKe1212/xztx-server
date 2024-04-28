package cn.iocoder.yudao.module.xztx.service.resume;

import cn.hutool.core.io.IoUtil;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.infra.api.file.FileApi;
import cn.iocoder.yudao.module.xztx.controller.app.vo.AppResumeUploadReqVO;
import cn.iocoder.yudao.module.xztx.dal.dataobject.resume.XztxResumeDO;
import cn.iocoder.yudao.module.xztx.dal.mysql.resume.XztxResumeMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.InputStream;

@Service
public class XztxResumeServiceImpl extends ServiceImpl<XztxResumeMapper, XztxResumeDO> implements XztxResumeService {

    @Resource
    private FileApi fileApi;

    @Override
    public boolean createResume(XztxResumeDO resumeDO) {
        return save(resumeDO);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String uploadResume(AppResumeUploadReqVO vo) throws Exception {
        MultipartFile file = vo.getFile();
        String path = vo.getPath();
        String resUrl = "";
        InputStream in = file.getInputStream();
        resUrl = fileApi.createFile(file.getOriginalFilename(), path, IoUtil.readBytes(in));


        XztxResumeDO resume = new XztxResumeDO();
        long count = count(new LambdaQueryWrapper<XztxResumeDO>().eq(XztxResumeDO::getCreator, SecurityFrameworkUtils.getLoginUserId()));
        if (count == 0) {
            // 第一次上传简历，设置为默认投递
            resume.setDefaultFlag('1');
        }
        resume.setUrl(resUrl);
        resume.setName(vo.getName());
        resume.setType("pdf");
        resume.setSize(vo.getSize());
        resume.setCreator(String.valueOf(SecurityFrameworkUtils.getLoginUserId()));
        createResume(resume);
        return resUrl;
    }

}
