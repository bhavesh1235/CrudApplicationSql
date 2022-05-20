package com.example.crudapplicationsql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
    private String bookId;

    private String bookName;

    private String author;

    private Integer year;

    private Double cost;

    private Integer bookRating;
}
