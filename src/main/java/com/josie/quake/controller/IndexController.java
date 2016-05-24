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

    @RequestMapping(value = "charts")
    public String charts() {
        return "charts";
    }

    @RequestMapping(value = "error")
    public String error() {
        return "error";
    }

    @RequestMapping(value = "/settings/setting")
    public String setting() {
        return "settings/setting";
    }

    @RequestMapping(value = "/settings/disaster")
    public String disaster() {
        return "settings/manage-disaster";
    }


    @RequestMapping(value = "/settings/whitelist")
    public String whitelist() {
        return "settings/manage-whitelist";
    }

    @RequestMapping(value = "/settings/examine")
    public String examine() {
        return "settings/manage-examine";
    }

    @RequestMapping(value = "/settings/warning")
    public String warning() {
        return "settings/manage-warning";
    }

    @RequestMapping(value = "/settings/user")
    public String user() {
        return "settings/manage-user";
    }



}
