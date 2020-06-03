package com.bookstore.repository;

import com.bookstore.domain.Publisher;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:data/data-dependents.xml")
class PublisherRepositoryDbUnitTest {

    @Autowired
    private PublisherRepository repository;


    @Test
    public void testFindPublisherById() {
        Optional<Publisher> result = repository.findById(1);
        assertThat(result.isPresent()).isTrue();
        assertThat(result.get().getPublisherID()).isEqualTo(1);
    }

    @Test
    public void testDeletePublisherById() {
        repository.delete(new Publisher(1, "O'Reilly Media"));
        Optional<Publisher> result = repository.findById(1);
        assertThat(result.isPresent()).isFalse();
    }

    @Test
    public void testSavePublisher()
    {
        Publisher p=new Publisher(1,"Neeraj");
        Publisher savedPublisher = repository.save(p);
        assertThat(savedPublisher).isNotNull();
        assertThat(savedPublisher.getPublisherID()).isEqualTo(1);
    }

    @Test
    public void testGetAllPublisher()
    {
        List<Publisher> all = repository.findAll();
        assertThat(all).isNotNull();
        assertThat(all.size()>0);
    }
}