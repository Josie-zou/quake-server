package com.josie.quake.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Glacier on 16/5/21.
 */
@Controller
@RequestMapping(value = "")
public class IndexController {

    @RequestMapping(value = "")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "index")
    public String _index() {
        return "index";
    }

    @RequestMapping(value = "showdata")
    public String showdata() {
        return "showdata";
    }

    @RequestMapping(value = "about")
    public String about() {
        return "about";
    }

}
