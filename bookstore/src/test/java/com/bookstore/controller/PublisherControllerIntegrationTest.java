package com.bookstore.controller;

import com.bookstore.domain.GenericResponse;
import com.bookstore.domain.Publisher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PublisherControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getPublisherById() {
        ResponseEntity<Publisher> response = restTemplate.getForEntity("/v1/publisher/1", Publisher.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getPublisherID()).isEqualTo(1);
    }

    @Test
    void getAllPublisher() {
        ResponseEntity<? extends List> response = restTemplate.getForEntity("/v1/publisher/all", new ArrayList<Publisher>().getClass());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().size()>0);
    }

    @Test
    void addPublisher() {

        Publisher p=new Publisher(0,"Neeraj");
        HttpEntity<Publisher> request=new HttpEntity<Publisher>(p,null);
        ResponseEntity<Publisher> responseEntity=restTemplate.postForEntity("/v1/publisher/add",request,Publisher.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getPublisherID()).isEqualTo(0);
    }

    @Test
    void updatePublisher() {
        Publisher p=new Publisher(1,"Neeraj");
        HttpEntity<Publisher> request=new HttpEntity<Publisher>(p,null);
        ResponseEntity<GenericResponse> responseEntity=restTemplate.postForEntity("/v1/publisher/update",request,GenericResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().isSuccess()).isTrue();
    }

    @Test
    void deletePublisherById() {
        ResponseEntity<GenericResponse> responseEntity=restTemplate.exchange("/v1/publisher/delete/1",HttpMethod.DELETE,null,GenericResponse.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().isSuccess()).isTrue();

    }
}