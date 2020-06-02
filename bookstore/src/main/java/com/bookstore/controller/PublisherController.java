package com.bookstore.controller;

import com.bookstore.domain.GenericResponse;
import com.bookstore.domain.Publisher;
import com.bookstore.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/v1/publisher")
public class PublisherController {

    private final PublisherService service;

    @Autowired
    public PublisherController(PublisherService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getPublisherById(@PathVariable Integer id){
        return service.getPublisherById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Publisher>> getAllPublisher(){
        return service.getAllPublishers();
    }


    @PostMapping(value = "/add",consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Publisher> addPublisher(@RequestBody Publisher publisher) {
        return service.savePublisher(publisher);
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> updatePublisher(@RequestBody Publisher publisher)
    {
        return service.updatePublisher(publisher);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GenericResponse> deletePublisherById(@PathVariable Integer id) {
        return service.deletePublisher(id);
    }
}
