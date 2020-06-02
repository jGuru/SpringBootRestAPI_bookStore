package com.bookstore.service;

import com.bookstore.domain.GenericResponse;
import com.bookstore.domain.Publisher;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.xml.ws.Response;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PublisherServiceUnitTest {

    @MockBean
    private PublisherService service;

    @Test
    void savePublisher() {
        Publisher publisher=new Publisher(-1,"Tanish");
        when(service.savePublisher(any(Publisher.class))).thenReturn(new ResponseEntity<>(publisher, HttpStatus.CREATED));
        ResponseEntity<Publisher> responseEntity = service.savePublisher(publisher);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody().getPublisherID()).isEqualTo(-1);
    }

    @Test
    void deletePublisher() {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(true);
        when(service.deletePublisher(any(Integer.class))).thenReturn(new ResponseEntity<>(genericResponse, HttpStatus.OK));
        ResponseEntity<GenericResponse> responseEntity = service.deletePublisher(1);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().isSuccess()).isTrue();

    }

    @Test
    void updatePublisher() {
        Publisher publisher=new Publisher(-1,"Tanish");
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(true);
        when(service.updatePublisher(any(Publisher.class))).
                thenReturn(new ResponseEntity<>(genericResponse, HttpStatus.OK));
        ResponseEntity<GenericResponse> responseEntity = service.updatePublisher(publisher);
        assertThat(responseEntity.getBody().isSuccess()).isTrue();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getPublisherById() {
        Publisher publisher=new Publisher(-1,"Tanish");
        when(service.getPublisherById(any(Integer.class))).
                thenReturn(new ResponseEntity<Publisher>(publisher,HttpStatus.OK));
        ResponseEntity<Publisher> responseEntity = service.getPublisherById(-1);
        assertThat(responseEntity.getBody().getPublisherID()).isEqualTo(-1);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void getAllPublishers() {
        List<Publisher> publisherList=new ArrayList<>();
        publisherList.add(new Publisher(1,"a"));
        publisherList.add(new Publisher(2,"b"));
        publisherList.add(new Publisher(3,"c"));
        ResponseEntity<List<Publisher>> responseEntity=new ResponseEntity<>(publisherList,HttpStatus.OK);
        when(service.getAllPublishers()).thenReturn(responseEntity);
        ResponseEntity<List<Publisher>> result = service.getAllPublishers();
        assertThat(responseEntity.getBody().size()>0);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}