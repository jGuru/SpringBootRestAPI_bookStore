package com.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity(name = "books")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Integer bookId;

    @NotNull
    private String title;

    @Column(name = "total_pages")
    private Integer totalPages;

    private Double rating;

    @Column(name = "published_date")
    private Date publishedDate;

    @Max(13)
    private String isbn;

    @ManyToOne()  //many-to-one mapping means that many instances of this entity are mapped to one instance of another entity –
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;


    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;
}
