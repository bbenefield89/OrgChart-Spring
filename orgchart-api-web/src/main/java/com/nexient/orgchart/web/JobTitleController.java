package com.nexient.orgchart.web;

import com.nexient.orgchart.data.entity.JobTitleEntity;
import com.nexient.orgchart.model.JobTitle;
import com.nexient.orgchart.service.JobTitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JobTitle readJobTitle(@PathVariable int jobTitleID){
        return titleService.findJobTitleByID(jobTitleID);
    }

    @RequestMapping(value = "/archives", method = RequestMethod.GET)
    public List<JobTitle> findAllArchivedMessages() {
        return titleService.findAllInactiveJobTitles();
    }

    @RequestMapping(method = RequestMethod.POST)
    public JobTitle createJobTitle(@Valid @RequestBody JobTitle title){
        return titleService.storeOrUpdate(title);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JobTitle updateJobTitle(@Valid @RequestBody JobTitle title){
        return titleService.storeOrUpdate(title);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public boolean deleteJobTitle(@PathVariable int jobTitleId){
        return titleService.removeJobTitle(jobTitleId);
    }

}
