package com.bookstore.service;
import static org.assertj.core.api.Assertions.*;

import com.bookstore.domain.GenericResponse;
import com.bookstore.domain.Publisher;
import com.bookstore.exception.PublisherNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PublisherServiceIntegrationTest {

    @Autowired
    private PublisherService service;
    @Test
    void savePublisher(){
        Publisher publisher=new Publisher(-1,"Tanish");
        ResponseEntity<Publisher> responseEntity=service.savePublisher(publisher);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getBody().getName()).isEqualTo(publisher.getName());
    }

    @Test
    void deletePublisher() {
        ResponseEntity<GenericResponse> result=service.deletePublisher(-1);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().isSuccess()).isTrue();
    }

    @Test
    void updatePublisher() {
        Publisher publisher=new Publisher(0,"Neeraj");
        ResponseEntity<GenericResponse> result = service.updatePublisher(publisher);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().isSuccess()).isTrue();
    }

    @Test
    void getPublisherById() {
        ResponseEntity<Publisher> responseEntity = service.getPublisherById(1);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().getPublisherID()).isEqualTo(1);
    }

    @Test
    void getAllPublishers() {
        ResponseEntity<List<Publisher>> responseEntity = service.getAllPublishers();
        assertThat(responseEntity.getBody().size()>0);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test()
    public void getPublisherById1()
    {
        assertThrows(PublisherNotFoundException.class, ()->service.getPublisherById(-2));
    }
}