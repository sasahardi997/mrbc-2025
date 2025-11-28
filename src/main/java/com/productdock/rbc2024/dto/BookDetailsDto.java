package com.productdock.rbc2024.dto;

public record BookDetailsDto(
    Long id,
    String title,
    String author,
    Integer numberOfPages
) {}
