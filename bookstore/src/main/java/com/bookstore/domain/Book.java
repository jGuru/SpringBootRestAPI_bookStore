package com.bookstore.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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


    @ManyToMany(mappedBy = "books")
    private Set<Author> authors;
}
