package cn.edu.usts.cs2020.main_system;

import cn.edu.usts.cs2020.main_system.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class MysystemApplicationTests {
    @Autowired
    UserDao dao;
    @Test
    public void contextLoads() {
//        Integer num = dao.insertUser("CP", "123", "326455704@qq.com");
//        System.out.println(num);
        Integer addAct = dao.addAct("聚餐","唱歌","12","LeafMing");
        System.out.println(addAct);
    }

}
