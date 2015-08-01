package com.dstevens.web.admin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserPageController {
    
    @RequestMapping(value = "/admin/page/users", method = RequestMethod.GET)
    public ModelAndView getUsersPage() {
        return new ModelAndView("/admin/users");
    }
}
