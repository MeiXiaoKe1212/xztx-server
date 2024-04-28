package cn.iocoder.yudao.module.xztx.controller.app.vo;

import lombok.Data;

/**
 * 招聘者可查看的投递者信息 VO
 */
@Data
public class MemberUserInfoVO {

    // 用户id
    private Long id;

    // 用户手机号
    private String mobile;

    // 用户昵称
    private String nickname;

    // 用户头像
    private String avatar;

    // 用户姓名
    private String name;

    // 用户性别
    private Integer sex;

}
