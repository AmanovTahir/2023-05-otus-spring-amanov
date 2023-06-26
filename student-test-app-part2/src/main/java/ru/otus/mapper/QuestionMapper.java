package ru.otus.mapper;

import org.springframework.stereotype.Component;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.dto.QuestionDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    private static Answer initAnswer(QuestionDto dto, int index, boolean correct) {
        return Answer.builder()
                .answer(dto.getAnswers().get(index))
                .correct(correct)
                .build();
    }

    public Question mapToQuestion(QuestionDto dto) {
        return Question.builder()
                .question(dto.getQuestion())
                .answers(getAnswersList(dto))
                .build();
    }

    public List<Question> mapToQuestionList(List<QuestionDto> list) {
        return list.stream()
                .filter(Objects::nonNull)
                .map(this::mapToQuestion)
                .collect(Collectors.toList());
    }

    private List<Answer> getAnswersList(QuestionDto dto) {
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < dto.getAnswers().size(); i++) {
            boolean correct = (dto.getCorrectNumber() - 1) == i;
            Answer answer = initAnswer(dto, i, correct);
            answers.add(answer);
        }
        return answers;
    }
}
