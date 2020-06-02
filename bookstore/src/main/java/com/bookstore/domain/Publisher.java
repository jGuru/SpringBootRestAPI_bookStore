package com.bookstore.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity(name = "publishers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Publisher {

    @Id
    @Column(name = "publisher_id")
    private Integer publisherID;
    @NotNull
    @Min(5)
    @Max(255)
    private String name;

    @OneToMany
    @JoinColumn(name = "publisher_id")
    private Set<Book> books;
}
