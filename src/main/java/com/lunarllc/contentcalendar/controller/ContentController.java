package com.lunarllc.contentcalendar.controller;

import com.lunarllc.contentcalendar.model.Content;
import com.lunarllc.contentcalendar.repository.ContentCollectionRespository;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    //we know the Content Controller depends on the ContentCollectionRepository so we will inject the CCR repo into the
    //constructor of the ContentController
    private final  ContentCollectionRespository repo;

    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);

    //when you have 1 public constructor, @Autowired is already implicit so techinically we dont need the bottom annotation
    @Autowired
    public ContentController(ContentCollectionRespository ccr){
        this.repo = ccr;
    }


    //make a request and find all the pieces of content in the system
    @GetMapping("")
    public List<Content> findAll(){
        logger.info("GET ALL CONTENT");
        return repo.findAll();
    }

    //we can build Read, Update, Delete as well as other Gets
    @GetMapping("/{id}")
    public Optional<Content> findById(@PathVariable Integer id) {
        logger.info("GET CONTENT BY ID: " + id.toString());
        return Optional.ofNullable(repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found.")));
    }

    //create a new Content
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Content content) {
        logger.info("POST CONTENT: " + content.toString());
        repo.createContent(content);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateById(@PathVariable Integer id, @RequestBody Content c){
        logger.info("UPDATE CONTENT BY ID: "+id.toString() + " " + c.toString());
        if(!repo.existById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found.");
        }
        repo.createContent(c);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id){
        logger.info("DELETE CONTENT BY ID: " + id.toString());
        if(!repo.existById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Content not found.");
        }
        repo.deleteContent(id);
    }


}
