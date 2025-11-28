package com.productdock.rbc2024.mapper;

import com.productdock.rbc2024.domain.Book;
import com.productdock.rbc2024.dto.BookDetailsDto;
import com.productdock.rbc2024.dto.BookDto;
import com.productdock.rbc2024.dto.EditBookDetailsDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDetailsDto convertModelToBookDetailsDto(Book book);

    @Mapping(target = "comments", ignore = true)
    BookDto convertModelToBookDto(Book book);

    @Mapping(target = "id", source = "bookDetailsDto.id")
    @Mapping(target = "title", source = "bookDetailsDto.title")
    @Mapping(target = "author", source = "bookDetailsDto.author")
    @Mapping(target = "numberOfPages", source = "bookDetailsDto.numberOfPages")
    Book convertBookDetailsDtoToModel(BookDetailsDto bookDetailsDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "title", source = "dto.title")
    @Mapping(target = "numberOfPages", source = "dto.numberOfPages")
    Book convertEditBookDetailsDtoToModel(EditBookDetailsDto dto, @MappingTarget Book bookFromDdb);
}
