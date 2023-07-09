package com.lunarllc.contentcalendar.controller;

import com.lunarllc.contentcalendar.model.Content;
import com.lunarllc.contentcalendar.repository.ContentCollectionRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    //we know the Content Controller depends on the ContentCollectionRepository so we will inject the CCR repo into the
    //constructor of the ContentController
    private final  ContentCollectionRespository repo;

    //when you have 1 public constructor, @Autowired is already implicit so techinically we dont need the bottom annotation
    @Autowired
    public ContentController(ContentCollectionRespository ccr){
        this.repo = ccr;
    }


    //make a request and find all the pieces of content in the system
    @GetMapping("")
    public List<Content> findAll(){
        return repo.findAll();
    }

    //we can build Read, Update, Delete as well as other Gets
}
