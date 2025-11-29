package com.productdock.rbc2024.domain.dto;

public record BookDetailsDto(
    Long id,
    String title,
    String author,
    Integer numberOfPages
) {}
