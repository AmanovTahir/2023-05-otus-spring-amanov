package ru.otus.library.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookDeserializer extends JsonDeserializer<BookDto> {

    @Override
    public BookDto deserialize
            (JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        ObjectMapper mapper = new ObjectMapper();

        String id = node.has("id") ? node.get("id").asText() : null;
        String title = node.get("title").asText();

        List<AuthorDto> authors = new ArrayList<>();
        JsonNode authorsNode = node.get("authors");
        for (JsonNode authorNode : authorsNode) {
            AuthorDto author = mapper.treeToValue(authorNode, AuthorDto.class);
            authors.add(author);
        }

        List<CategoryDto> categories = new ArrayList<>();
        JsonNode categoriesNode = node.get("categories");
        for (JsonNode categoryNode : categoriesNode) {
            CategoryDto category = mapper.treeToValue(categoryNode, CategoryDto.class);
            categories.add(category);
        }
        return new BookDto(id, title, authors, categories);
    }
}
