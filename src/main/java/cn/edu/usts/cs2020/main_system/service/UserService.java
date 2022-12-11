package cn.edu.usts.cs2020.main_system.service;

import cn.edu.usts.cs2020.main_system.entity.Activity;
import cn.edu.usts.cs2020.main_system.entity.Confirm;
import cn.edu.usts.cs2020.main_system.entity.User;

import java.util.List;

public interface UserService {
    //登录验证
    User login(String u_name, String u_password);

    //用户注册
    Integer insertUser(String u_name,String u_password,String u_email);

    //创建活动
    Integer createAct(String act_name,String act_time,String act_location,String actbasicmoney,String creater);

    //查询活动
    List<Activity> getAllAct();

    //报名活动
    Integer signUp(String u_name, String u_email, String act_name, String act_time, String act_location,String actbasicmoney);

    //查询我创建的活动
    List<Activity> myAct(String creater);

    //查询我加入的活动
    List<Confirm> joined(String u_name);

    //查询费用
    List<Confirm> money(String u_name);

    //追加项目
    Integer addAct(String act_name,String act1,String money1,String u_name);

//    //生成报告
//    List<Confirm> toExcel(String act_name,String act_time,String act_location,String actbasicmoney,String act1,String money1);
    List<Confirm> toExcel(String u_name);
}
