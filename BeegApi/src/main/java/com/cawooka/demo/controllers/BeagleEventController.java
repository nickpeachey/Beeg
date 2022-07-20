package com.cawooka.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/beagle/events")
public class BeagleEventController {

    @GetMapping("/put")
    public ResponseEntity<String> put() {

        return new ResponseEntity<>("hello willow", HttpStatus.OK);
    }
}
