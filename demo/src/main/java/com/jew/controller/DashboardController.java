package com.jew.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.jew.models.JwtResponse;
import com.jew.models.Member;
import com.jew.models.Menu;
import com.jew.service.AccessService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

    @Autowired
    AccessService accessService;

    @PostMapping("/dashboard")
    public String goDashboard(JwtResponse model, HttpSession httpSession) throws Exception {

        
        //model에 담긴 유저 Id나 시퀀스를 가지고
        //해당 유저가 접근 가능한 메뉴,권한 정보를 서버에서 관리하게 한다.
        //model
        
        //1. 사용자 기본정보 (이름, id, 연락처...)
        Member member = accessService.getUserInfo(model.getUserId());
        //2. 사용자 메뉴 권한 정보 
        ArrayList<Menu> authMenus = accessService.myMenuList(model.getUserId());
        

        httpSession.setAttribute("USER_INFO", member);
        httpSession.setAttribute("AUTH_MENUS", authMenus);
        
        return "dashboard";
    }


}
