package ru.otus.library.mapper;

import org.mapstruct.MapperConfig;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */
@MapperConfig(componentModel = "spring")
public interface MappingConfig<D, E> {
    D toDto(E entity);

    List<D> toDtoList(List<E> entities);

    E toDomain(D dto);

    List<E> toDomainList(List<D> dtoList);
}