package com.CovidApp.covidApp.Service;


import com.CovidApp.covidApp.Utility.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class CovidDataService {

    @Autowired
    RestTemplate restTemplate;

    public Integer getCovidData(String cityName)
    {

        Map<String, String> stateCityMap=new HashMap<>();
        stateCityMap.put("Agra","Uttar Pradesh");
        stateCityMap.put("Rampur","Uttar Pradesh");
        stateCityMap.put("Noida","Uttar Pradesh");
        stateCityMap.put("Hapur","Uttar Pradesh");
        stateCityMap.put("Amritsar","Punjab");
        stateCityMap.put("Barnala","Punjab");
        stateCityMap.put("Bathinda","Punjab");
        stateCityMap.put("Surat","Gujarat");
        stateCityMap.put("Ahmedabad","Gujarat");
        stateCityMap.put("Pune","Maharashtra");
        stateCityMap.put("Mumbai","Maharashtra");

        Map map = restTemplate.getForObject(Util.COVID_DATA_API, Map.class);
        if (map == null) {
            throw new IllegalStateException("Failed to retrieve data from API");
        }

        Object rawCityObjectMap = map.get(stateCityMap.get(cityName));
        if (!(rawCityObjectMap instanceof Map)) {
            throw new IllegalStateException("Expected a Map for cityObjectMap but got " + rawCityObjectMap);
        }
        Map<String, Object> cityObjectMap = (Map<String, Object>) rawCityObjectMap;

        Object rawDistrictData = cityObjectMap.get("districtData");
        if (!(rawDistrictData instanceof Map)) {
            throw new IllegalStateException("Expected a Map for districtData but got " + rawDistrictData);
        }
        Map<String, Object> districtData = (Map<String, Object>) rawDistrictData;

        Object rawCity = districtData.get(cityName);
        if (!(rawCity instanceof Map)) {
            throw new IllegalStateException("Expected a Map for city but got " + rawCity);
        }
        Map<String, Object> city = (Map<String, Object>) rawCity; // This cast is now safe
        Integer active = (Integer) city.get("active");
        return active;
    }
}