package com.CovidApp.covidApp.Controller;

import com.CovidApp.covidApp.Service.CovidDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@RequestMapping("/covid-data")

public class CovidController{

    @Autowired
    CovidDataService covidDataService;
    //localhost:8080/covid-data/citywise/Delhi
    @GetMapping("/citywise/{cityName}") //{this brackets is to declare dynamic variable}
    //the path variable will automatically detect dynamic variable's input and map it to the string city
    public String getCovidCases(@PathVariable String cityName) {

        Integer covidDataData = covidDataService.getCovidData(cityName);
        return "Covid Active Cased in "+cityName+ " = " +covidDataData;
    }

}
