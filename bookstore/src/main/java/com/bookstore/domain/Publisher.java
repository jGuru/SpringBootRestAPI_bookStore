package com.bookstore.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity(name = "publishers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "publisher_id")
    private Integer publisherID;
    @NotNull
    @Min(-1)
    @Max(255)
    private String name;

    /*@OneToMany
    @JoinColumn(name = "publisher_id")
    private Set<Book> books;*/
}
