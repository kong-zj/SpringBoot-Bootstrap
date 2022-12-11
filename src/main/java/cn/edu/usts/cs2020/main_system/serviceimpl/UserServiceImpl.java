package cn.edu.usts.cs2020.main_system.serviceimpl;

import cn.edu.usts.cs2020.main_system.dao.UserDao;
import cn.edu.usts.cs2020.main_system.entity.Activity;
import cn.edu.usts.cs2020.main_system.entity.Confirm;
import cn.edu.usts.cs2020.main_system.entity.User;
import cn.edu.usts.cs2020.main_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    //登录验证
    @Override
    public User login(String u_name,String u_password){
        return userDao.getInfo(u_name,u_password);
    }

    //用户注册
    @Override
    public Integer insertUser(String u_name,String u_password,String u_email){
        return userDao.insertUser(u_name,u_password,u_email);
    }
//    @Override
//    public int insertUser(User user){
//        return userDao.insertUser(user);
//    }


    //创建活动
    @Override
    public Integer createAct(String act_name,String act_time,String act_location,String actbasicmoney,String creater){
        return userDao.createAct(act_name,act_time,act_location,actbasicmoney,creater);
    }

    //查询活动信息
    @Override
    public List<Activity> getAllAct(){
        return userDao.getAllAct();
    }

    //报名活动
    @Override
    public Integer signUp(String u_name, String u_email, String act_name, String act_time, String act_location,String actbasicmoney){
        return userDao.signUp(u_name,u_email,act_name,act_time,act_location,actbasicmoney);
    }

    //查询我创建的活动
    @Override
    public List<Activity> myAct(String creater){
        return userDao.myAct(creater);
    }

    //查询我加入的活动
    @Override
    public  List<Confirm> joined(String u_name){
        return userDao.joined(u_name);
    }

    //查询费用
    @Override
    public List<Confirm> money(String u_name){
        return userDao.money(u_name);
    }

    //追加项目
    @Override
    public Integer addAct(String act_name,String act1,String money1,String u_name){
        return userDao.addAct(act_name,act1,money1,u_name);
    }

//    //生成报告
//    @Override
//    public List<Confirm> toExcel(String act_name,String act_time,String act_location,String actbasicmoney,String act1,String money1){
//        return userDao.toExcel(act_name,act_time,act_location,actbasicmoney,act1,money1);
//    }
    @Override
    public List<Confirm> toExcel(String u_name){
        return userDao.toExcel(u_name);
    }
}
