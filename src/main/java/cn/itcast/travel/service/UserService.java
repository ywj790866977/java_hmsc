package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {

    /**
     * @Description:  注册
     * @Date:  2019/8/19
     * @Param: [user]
     * @return:  boolean
     */
    boolean regist(User user);

    /**
     * @Description:  激活
     * @Date:  2019/8/19
     * @Param: [code]
     * @return:  boolean
     */
    boolean active(String code);

    /**
     * @Description:  登录
     * @Date:  2019/8/19
     * @Param: [user]
     * @return:  cn.itcast.travel.domain.User
     */
    User login(User user);
}
