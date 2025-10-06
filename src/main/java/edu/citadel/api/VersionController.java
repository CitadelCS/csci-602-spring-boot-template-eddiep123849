package edu.citadel.api;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class VersionController {
    @GetMapping("/version")
    public String getVersion() {
        System.out.println("IT'S WORKING!");
        return "1.0";
    }
}