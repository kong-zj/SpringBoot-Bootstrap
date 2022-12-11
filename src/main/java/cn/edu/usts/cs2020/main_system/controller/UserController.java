package cn.edu.usts.cs2020.main_system.controller;

import cn.edu.usts.cs2020.main_system.entity.Activity;
import cn.edu.usts.cs2020.main_system.entity.Confirm;
import cn.edu.usts.cs2020.main_system.entity.User;
import cn.edu.usts.cs2020.main_system.service.UserService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    JavaMailSender jms;

    //用户登录
    @RequestMapping(value = "/userloginIn",method = RequestMethod.POST)
    public String login(String u_name, String u_password,HttpSession session){
        User user = userService.login(u_name,u_password);
        if(user!=null){
//            System.out.println("Debug==>" + user.getU_name());
            session.setAttribute("user",user);
            return "personalpage";
        }else {
            return "index";
        }
    }

    //用户注册
    @RequestMapping("/createUser")
    public String createUser(String u_name,String u_password,String u_email){
        Integer integer = userService.insertUser(u_name, u_password, u_email);
        return "index";
    }

    //退出登录
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "index";
    }

    //创建活动
    @RequestMapping("/createAct")
    public String createAct(String act_name, String act_time, String act_location, String actbasicmoney,String creater) {
        Integer activity = userService.createAct(act_name, act_time, act_location, actbasicmoney, creater);
        return "personalpage";
    }


    //查询活动信息
    @RequestMapping("/getAllAct")
    public String getAllAct(ModelMap modelMap){
        List<Activity> activities = userService.getAllAct();
        modelMap.addAttribute("activities",activities);
        return "joinactivity";
    }

    //加入活动
    @RequestMapping("/joinAct")
    public String joinAct(String u_name, String u_email, String act_name, String act_time, String act_location,String actbasicmoney){
        Integer confirm = userService.signUp(u_name,u_email,act_name,act_time,act_location,actbasicmoney);
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom("2352664186@qq.com");
        //接收者
        mainMessage.setTo(u_email);
        //发送的标题
        mainMessage.setSubject("参加活动成功提醒");
        //发送的内容
        mainMessage.setText("您报名加入的"+act_name+"活动已成功，请准时参加活动");
        jms.send(mainMessage);
        return "personalpage";
    }

    //查询我创建的活动
    @RequestMapping("/myAct/{creater}")
    public String myAct(@PathVariable String creater,ModelMap modelMap){
         List<Activity> myact = userService.myAct(creater);
         modelMap.addAttribute("myacts",myact);
         return "myactivity";
    }

    //查询我加入的活动
    @RequestMapping("/joined/{u_name}")
    public String joined(@PathVariable String u_name,ModelMap modelMap){
        List<Confirm> joinedAct = userService.joined(u_name);
        modelMap.addAttribute("joined",joinedAct);
        return "joinedactivity";
    }

    //查询费用
    @RequestMapping("/money/{u_name}")
    public String money(@PathVariable String u_name,ModelMap modelMap){
        List<Confirm> money = userService.money(u_name);
        modelMap.addAttribute("allmoney",money);
        return "paybills";
    }

    //追加项目
    @RequestMapping("/addAct")
    public String addAct(String act_name,String act1,String money1,String u_name){
        System.out.println("act_name:"+act_name);
        System.out.println("act1:"+act1);
        System.out.println("money1:"+money1);
        System.out.println("u_name:"+u_name);
        Integer addAct = userService.addAct(act_name,act1,money1,u_name);
        return "personalpage";
    }

    //生成报告
    @RequestMapping("/toExcel/{u_name}")
    public String toExcel(@PathVariable String u_name,ModelMap modelMap){
        List<Confirm> excel = userService.toExcel(u_name);
        modelMap.addAttribute("excels",excel);
        return "printbills";
    }

    //下载excel
    @RequestMapping("/download/{u_name}")
    public void downloadAll(@PathVariable String u_name,HttpServletResponse response)throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("活动报告");
        List<Confirm> list = userService.toExcel(u_name);
        String filename = "活动报告" + ".xls";
        int rowNum = 1;
        String[] headers = {"活动名称","活动时间","活动地点","基本费用","追加活动","追加费用"};
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for(Confirm confirm:list){
            HSSFRow row1 = sheet.createRow(rowNum);
            row1.createCell(0).setCellValue(confirm.getAct_name());
            row1.createCell(1).setCellValue(confirm.getAct_time());
            row1.createCell(2).setCellValue(confirm.getAct_location());
            row1.createCell(3).setCellValue(confirm.getActbasicmoney());
            row1.createCell(4).setCellValue(confirm.getAct1());
            row1.createCell(5).setCellValue(confirm.getMoney1());
            rowNum++;
        }

        try {
            filename = URLEncoder.encode(filename, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/octet-stream"); //二进制流
        response.setHeader("Content-disposition","attachment;filename="+filename);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }

    @RequestMapping("/toCreate")
    public String toCreate(){
        return "createactivity";
    }

}
