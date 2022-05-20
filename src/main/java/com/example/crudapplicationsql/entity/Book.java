package com.example.crudapplicationsql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    private String bookId;

    private String bookName;

    private String author;

    private Integer year;

    private Double cost;

    private Integer bookRating;

    private Date creationDate;

    private Date updationDate;

    private Boolean isDeleted;
}
