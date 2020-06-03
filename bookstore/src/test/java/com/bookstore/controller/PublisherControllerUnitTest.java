package com.bookstore.controller;

import com.bookstore.domain.GenericResponse;
import com.bookstore.domain.Publisher;
import com.bookstore.service.PublisherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PublisherController.class)
class PublisherControllerUnitTest {

    @MockBean
    private PublisherService service;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void getPublisherById() throws Exception {
        when(service.getPublisherById(any(Integer.class))).
                thenReturn(new ResponseEntity<Publisher>(new Publisher(1,"Neeraj"), HttpStatus.OK));
        MvcResult mvcResult = mockMvc.perform(get("/v1/publisher/1")).andExpect(status().isOk()).andDo(print()).andReturn();
        Publisher found=mapper.readValue(mvcResult.getResponse().getContentAsString(),Publisher.class);
        assertThat(found.getPublisherID()).isEqualTo(1);

    }

    @Test
    void getAllPublisher() throws Exception {


        List<Publisher> publisherList=List.of(new Publisher(1,"Neeraj"),
                                              new Publisher(2,"Nidhi"),
                                              new Publisher(3,"Mihika"),
                                              new Publisher(4,"Tanish"),
                                              new Publisher(5,"Manvi")
                                             );
        ResponseEntity<List<Publisher>> responseEntity=new ResponseEntity<List<Publisher>>(publisherList,HttpStatus.OK);
        when(service.getAllPublishers()).thenReturn(responseEntity);
        MvcResult mvcResult = mockMvc.perform(get("/v1/publisher/all")).andExpect(status().isOk()).andDo(print()).andReturn();
        List<Publisher> foundList=mapper.readValue(mvcResult.getResponse().getContentAsString(),publisherList.getClass());
        assertThat(foundList.size()).isEqualTo(5);
    }

    @Test
    void addPublisher() throws Exception {
        Publisher p=new Publisher(1,"Neeraj");
        when(service.savePublisher(any(Publisher.class))).thenReturn(new ResponseEntity<>(p, HttpStatus.CREATED));
        MvcResult mvcResult = mockMvc.perform(post("/v1/publisher/add").contentType(MediaType.APPLICATION_JSON).
                content(mapper.writeValueAsString(p))).andExpect(status().isCreated()).andDo(print()).andReturn();
        Publisher savedPublisher=mapper.readValue(mvcResult.getResponse().getContentAsString(),Publisher.class);
        assertThat(savedPublisher).isNotNull();
        assertThat(savedPublisher.getPublisherID()).isEqualTo(1);
    }

    @Test
    void updatePublisher() throws Exception {
        Publisher p=new Publisher(1,"Neeraj");
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(true);
        when(service.updatePublisher(any(Publisher.class))).thenReturn(new ResponseEntity<>(genericResponse, HttpStatus.OK));
        MvcResult mvcResult = mockMvc.perform(post("/v1/publisher/update").content(mapper.writeValueAsString(p)).contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andDo(print()).andReturn();
        GenericResponse response =mapper.readValue(mvcResult.getResponse().getContentAsString(),GenericResponse.class);
        assertThat(response).isNotNull();
        assertThat(genericResponse.isSuccess()).isTrue();

    }

    @Test
    void deletePublisherById() throws Exception {
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setSuccess(true);
        ResponseEntity<GenericResponse> responseEntity = new ResponseEntity<>(genericResponse, HttpStatus.OK);
        when(service.deletePublisher(any(Integer.class))).thenReturn(responseEntity);
        MvcResult mvcResult = mockMvc.perform(delete("/v1/publisher/delete/1")).andExpect(status().isOk()).andReturn();
        GenericResponse response=mapper.readValue(mvcResult.getResponse().getContentAsString(),GenericResponse.class);
        assertThat(response).isNotNull();
        assertThat(response.isSuccess()).isTrue();
    }
}