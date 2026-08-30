package com.example.library.dto;

import com.example.library.model.Genre;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class BookRequest {

    @NotBlank(message = "ISBN is required")
    @Pattern(
            regexp = "^\\d{3}-\\d{10}$",
            message = "ISBN must match format 123-1234567890"
    )
    private String isbn;

    @NotBlank(message = "Title is required")
    @Size(min = 2, max = 120,
            message = "Title must be between 2 and 120 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 60,
            message = "Author must not exceed 60 characters")
    private String author;

    @NotNull(message = "Genre is required")
    private Genre genre;

    @NotNull(message = "Total copies is required")
    @Min(value = 1, message = "Total copies must be at least 1")
    @Max(value = 500, message = "Total copies must not exceed 500")
    private Integer totalCopies;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "50.00",
            message = "Price must be at least 50.00")
    @DecimalMax(value = "20000.00",
            message = "Price must not exceed 20000.00")
    @Digits(integer = 5, fraction = 2,
            message = "Price must have at most 5 integer digits and 2 decimal places")
    private BigDecimal price;

    private Integer publishedYear;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
}
