package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

import java.util.UUID;

/**
 * @ClassName: UserServiceImpl
 * @Description:
 * @Author: niunan
 * @Create: 2019/8/19 8:51
 **/
public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();
    /**
     * @Description:  注册
     * @Date:  2019/8/19
     * @Param: [user]
     * @return:  boolean
     */
    @Override
    public boolean regist(User user) {
        //1.判断用户是否存在
        User u = dao.findByUsername(user.getUsername());
        if(u != null){
            return false;
        }
        //2保存用户
        //2.1 获取激活码
        user.setCode(UuidUtil.getUuid());
        //2.2 设置激活状态
        user.setStatus("N");
        //保存用户
        dao.save(user);

        //3.发送激活邮件
        String content = "<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>点击激活,眼老哥旅游网</a>";

        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    /**
     * @Description:  激活
     * @Date:  2019/8/19
     * @Param: [code]
     * @return:  boolean
     */
    @Override
    public boolean active(String code) {
        User user = dao.findByCode(code);
        if(user != null){
            dao.updateStatus(user);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public User login(User user) {
        return dao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
