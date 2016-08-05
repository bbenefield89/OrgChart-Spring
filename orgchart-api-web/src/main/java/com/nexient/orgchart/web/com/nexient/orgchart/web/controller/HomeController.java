package com.nexient.orgchart.web.com.nexient.orgchart.web.controller;

import com.nexient.orgchart.web.View;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mrangel on 7/26/2016.
 */
@RestController
@RequestMapping("/home")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String doGet() {
        return View.HOME;
    }

}
