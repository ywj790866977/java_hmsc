package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {

    /**
     * @Description:  通过字符串查询用户是否存在
     * @Date:  2019/8/19
     * @Param: [username]
     * @return:  cn.itcast.travel.domain.User
     */
    public User findByUsername(String username);

    /**
     * @Description:  创建用户
     * @Date:  2019/8/19
     * @Param: [user]
     * @return:  void
     */
    public void save(User user);

    /**
     * @Description:  查询激活码
     * @Date:  2019/8/19
     * @Param: [code]
     * @return:  cn.itcast.travel.domain.User
     */
    User findByCode(String code);

    /**
     * @Description:  修改状态
     * @Date:  2019/8/19
     * @Param: [user]
     * @return:  void
     */
    void updateStatus(User user);

    /**
     * @Description:  查询用户登录
     * @Date:  2019/8/19
     * @Param: [username, password]
     * @return:  cn.itcast.travel.domain.User
     */
    User findByUsernameAndPassword(String username, String password);
}
