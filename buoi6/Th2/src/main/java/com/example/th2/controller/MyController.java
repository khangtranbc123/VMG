package com.example.th2.controller;

import com.example.th2.entity.City;
import com.example.th2.service.ICityService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class MyController {
    private final ICityService cityService;

    public MyController(ICityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping(value = "/cities/{id}")
    public City getCity(@PathVariable Long id) {

        return cityService.findById(id);
    }

    @PostMapping(value = "/cities", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public City createCity(@RequestBody @Valid City city) {

        return cityService.save(city);
    }

    @GetMapping(value = "/cities")
    public List<City> findAll() {

        return cityService.findAll();
    }
}
