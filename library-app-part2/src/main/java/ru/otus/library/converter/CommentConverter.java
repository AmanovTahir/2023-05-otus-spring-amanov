package ru.otus.library.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.otus.library.domain.Comment;

@Component
public class CommentConverter implements Converter<Comment, String> {

    @Override
    public String convert(Comment comment) {
        return "id." + comment.getId() + " — " + comment.getText();
    }
}
