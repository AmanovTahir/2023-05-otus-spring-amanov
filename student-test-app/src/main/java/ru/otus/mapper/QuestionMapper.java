package ru.otus.mapper;

import ru.otus.domain.Answer;
import ru.otus.domain.Question;
import ru.otus.dto.QuestionRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionMapper {

    public Question mapToQuestion(QuestionRecord record) {
        return new Question(record.getQuestion(), getAnswersList(record));
    }

    public List<Question> mapToQuestionList(List<QuestionRecord> list) {
        return list.stream()
                .map(this::mapToQuestion)
                .collect(Collectors.toList());
    }

    private List<Answer> getAnswersList(QuestionRecord record) {
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < record.getAnswers().size(); i++) {
            boolean correct = (record.getCorrectNumber() - 1) == i;
            Answer answer = new Answer(record.getAnswers().get(i), correct);
            answers.add(answer);
        }
        return answers;
    }
}
