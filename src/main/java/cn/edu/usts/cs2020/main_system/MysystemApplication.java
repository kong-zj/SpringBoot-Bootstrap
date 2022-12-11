package cn.edu.usts.cs2020.main_system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.usts.cs2020.main_system.dao")
public class MysystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysystemApplication.class, args);
    }

}
