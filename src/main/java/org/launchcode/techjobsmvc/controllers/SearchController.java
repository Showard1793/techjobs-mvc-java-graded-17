package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.
    @PostMapping(value = "results")                                             //X TODO #3.1: configure correct mapping type/route by referencing "form" tag in search.html
    public String displaySearchResults(Model model,                     //X TODO #3.2: displaySearchResults takes in a Model parameter
                                       @RequestParam String searchType, //X TODO #3.3 & 3.4: take in 2 other parameters: searchType and searchTerm (found in search.html)
                                       @RequestParam String searchTerm) {
        ArrayList<Job> jobs;

        // Check if search term is "all" or empty and adds appropriate job data to "jobs" ArrayList
        if (searchTerm.equalsIgnoreCase("all") || searchTerm.isEmpty()) {  //X TODO #3.5: If the user enters “all” in the search box, or if they leave the box empty, call the findAll() method from JobData. Otherwise, send the search information to findByColumnAndValue. In either case, store the results in a jobs ArrayList.
            jobs = JobData.findAll();
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        // Add jobs and column choices to model
        model.addAttribute("jobs", jobs);                  //X TODO #3.6: Pass jobs into the search.html view via the model parameter.
        model.addAttribute("columns", ListController.columnChoices);      //X TODO #3.7: Pass ListController.columnChoices into the view, as the existing search handler does.

        return "search";
    }

}