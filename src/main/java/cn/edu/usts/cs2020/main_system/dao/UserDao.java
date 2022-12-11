package cn.edu.usts.cs2020.main_system.dao;

import cn.edu.usts.cs2020.main_system.entity.Activity;
import cn.edu.usts.cs2020.main_system.entity.Confirm;
import cn.edu.usts.cs2020.main_system.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserDao {
    //登录验证
    User getInfo(@Param("u_name") String u_name, @Param("u_password") String u_password);

    //用户注册
    Integer insertUser(@Param("u_name") String u_name,@Param("u_password") String u_password,@Param("u_email") String u_email);

    //创建活动
    Integer createAct(@Param("act_name") String act_name,@Param("act_time")String act_time, @Param("act_location")String act_location,
                      @Param("actbasicmoney")String actbasicmoney, @Param("creater")String creater);

    //查询活动
    List<Activity> getAllAct();

    //报名活动
    Integer signUp(@Param("u_name") String u_name,@Param("u_email")String u_email,@Param("act_name")String act_name,
                   @Param("act_time")String act_time, @Param("act_location")String act_location,@Param("actbasicmoney")String actbasicmoney);

    //查询我创建的活动
    List<Activity> myAct(@Param("creater")String creater);

    //查询我加入的活动
    List<Confirm> joined(@Param("u_name")String u_name);

    //查询费用
    List<Confirm> money(@Param("u_name")String u_name);

    //追加项目
    Integer addAct(@Param("act_name")String act_name,@Param("act1") String act1,@Param("money1")String money1,
                   @Param("u_name")String u_name);

//    //生成报告
//    List<Confirm> toExcel(@Param("act_name")String act_name,@Param("act_time")String act_time,@Param("act_location")String act_location,
//                          @Param("actbasicmoney")String actbasicmoney,@Param("act1")String act1,@Param("money1")String money1);
    List<Confirm> toExcel(@Param("u_name")String u_name);
}
