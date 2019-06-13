package com.leekoko.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.leekoko.pojo.Problem;
import com.leekoko.pojo.User;
import com.leekoko.service.IChapterService;
import com.leekoko.service.IProblemService;
import com.leekoko.service.IUserService;
import com.leekoko.util.HGPageProcesser;
import com.leekoko.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author liyb
 * @since 2019-06-04
 */
@Controller
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private IProblemService problemService;

    @Autowired
    private IChapterService chapterService;

    @Autowired
    private IUserService userService;

    /**
     * 登陆网站，获取token
     * @return
     */
    @RequestMapping("/loginWeb")
    @ResponseBody
    public HashMap<String, Object> loginWeb(@RequestParam(value = "userName", required = true)String userName, @RequestParam(value = "password", required = true)String password){
        String param = "j_username="+ userName +"&j_password="+password;
        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setLoginTime(new Date().toString());
        userService.saveOrUpdate(user);
        String cookie = null;
        try {
            cookie = HttpUtil.doPost("https://wl.scutde.net:443/edu3/j_spring_security_check?"+ param,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("data",cookie);
        return result;
    }

    /**
     * 爬取保存问题
     */
    @RequestMapping("/saveProblem")
    @ResponseBody
    public HashMap<String,Object> saveProblem(@RequestParam(value = "cookie", required = true)String cookie, @RequestParam(value = "userName", required = true)String userName, HttpServletRequest request){
        HGPageProcesser processer = new HGPageProcesser(problemService,chapterService,request);
        processer.startCrawl(cookie, userName);
        //开始返回答案
        HashMap<String, Object> result = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        result.put("chapterList",session.getAttribute("chapterList"));
        result.put("chapterProblemList",session.getAttribute("chapterProblemList"));
        return result;
    }

    /**
     * 根据id获取题目答案
     */
    @RequestMapping("/getDetailById")
    @ResponseBody
    public HashMap<String,Object> getDetailById(String problemId, HttpServletRequest request){
        Problem problem = problemService.getOne(new QueryWrapper<Problem>().eq("ID", problemId));
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("problem",problem);
        return result;
    }

}

