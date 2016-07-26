package com.nexient.orgchart.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mrangel on 7/26/2016.
 */
public class HomeController {

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String doGet() {
        return View.HOME;
    }

}
