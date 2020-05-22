package com.odds.springtestsdemo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class RepositoryController {
    @Autowired
    RepositoryService repositoryService;

    @GetMapping(value = "/users/{user}/repository")
    public ResponseEntity<RepositoryModel> getRepositoryForUser(HttpServletRequest request, @PathVariable String user) {
        RepositoryModel response = null;

        try {
            response = repositoryService.getRepository(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
