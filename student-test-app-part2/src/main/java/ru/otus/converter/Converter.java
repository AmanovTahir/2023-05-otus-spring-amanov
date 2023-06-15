package ru.otus.converter;

public interface Converter<T, C> {

    C convert(T t);
}
