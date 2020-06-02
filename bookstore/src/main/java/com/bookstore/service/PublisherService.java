package com.bookstore.service;

import com.bookstore.domain.GenericResponse;
import com.bookstore.domain.Publisher;
import com.bookstore.exception.PublisherNotFoundException;
import com.bookstore.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PublisherService {

    private final PublisherRepository repository;

    @Autowired
    public PublisherService(PublisherRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity<Publisher> savePublisher(Publisher publisher){
        repository.save(publisher);
        return new ResponseEntity<>(publisher, HttpStatus.CREATED);
    }

    public ResponseEntity<GenericResponse> deletePublisher(Integer id) throws PublisherNotFoundException{
        Optional<Publisher> foundPublisher=repository.findById(id);
        if(foundPublisher.isPresent()){
            repository.delete(foundPublisher.get());
            GenericResponse genericResponse = new GenericResponse();
            genericResponse.setSuccess(true);
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
        }
        else{
            throw new PublisherNotFoundException("The Specified publisher not found");
        }
    }

    public ResponseEntity<GenericResponse> updatePublisher(Publisher publisher){
            repository.save(publisher);
            GenericResponse genericResponse = new GenericResponse();
            genericResponse.setSuccess(true);
            return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    public ResponseEntity<Publisher> getPublisherById(Integer id) throws PublisherNotFoundException
    {
        Optional<Publisher> foundPublisher=repository.findById(id);
        if(foundPublisher.isPresent()){
            return new ResponseEntity<>(foundPublisher.get(),HttpStatus.OK);
        }
        else{
            throw new PublisherNotFoundException("The Specified publisher not found");
        }
    }

    public ResponseEntity<List<Publisher>> getAllPublishers(){
        List<Publisher>  publisherList=repository.findAll();
        return new ResponseEntity<>(publisherList,HttpStatus.OK);
    }
}
