package com.nexient.orgchart.web.com.nexient.orgchart.web.controller;

import com.nexient.orgchart.model.JobTitle;
import com.nexient.orgchart.service.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by mrangel on 7/26/2016.
 */
@RestController
@RequestMapping("/titles")
public class JobTitleController {

    @Autowired
    private JobTitleService titleService;

    @RequestMapping(method = RequestMethod.GET)
    public List<JobTitle> getAllActiveJobTitles(){
        return titleService.findAllActiveJobTitles();
    }

    @RequestMapping(value = "/{jobTitleId}", method = RequestMethod.GET)
    public JobTitle readJobTitle(@PathVariable int jobTitleId){
        return titleService.findJobTitleByID(jobTitleId);
    }

    @RequestMapping(value = "/archives", method = RequestMethod.GET)
    public List<JobTitle> findAllArchivedJobTitles() {
        return titleService.findAllInactiveJobTitles();
    }

    @RequestMapping(method = RequestMethod.POST)
    public JobTitle createJobTitle(@Valid JobTitle title){
        return titleService.storeOrUpdate(title);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JobTitle updateJobTitle(@Valid JobTitle title){
        return titleService.storeOrUpdate(title);
    }

    @RequestMapping(value = "/{jobTitleId}", method = RequestMethod.DELETE)
    public boolean deleteJobTitle(@PathVariable int jobTitleId){
        return titleService.removeJobTitle(jobTitleId);
    }

}
