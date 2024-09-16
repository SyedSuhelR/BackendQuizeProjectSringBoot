package com.quizeBackend.quizeApp.Service;

import com.quizeBackend.quizeApp.Model.Question;
import com.quizeBackend.quizeApp.Model.QuestionWrapper;
import com.quizeBackend.quizeApp.Model.Quize;
import com.quizeBackend.quizeApp.Model.Responses;
import com.quizeBackend.quizeApp.dao.QuestionDao;
import com.quizeBackend.quizeApp.dao.QuizeDtao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuizeService {

    @Autowired
    QuizeDtao quizeDao;
    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuize(String categiry, int num, String title) {

        List<Question> questions=questionDao.findRandomQuiestionByCategory(categiry,num);
        Quize quize=new Quize();
        quize.setTitle(title);
        quize.setQuestions(questions);
        quizeDao.save(quize);

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizeQuestionById(Integer id) {
        Optional<Quize> quize=quizeDao.findById(id);
        List<Question> questionsFormdb=quize.get().getQuestions();
        List<QuestionWrapper> questionForUser=new ArrayList<>();
        for (Question q:questionsFormdb){
            QuestionWrapper qw=new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionForUser.add(qw);
        }

        return new  ResponseEntity<>(questionForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculate(Integer id, List<Responses >responses) {
        Optional<Quize> quize=quizeDao.findById(id);
        List<Question> questions=quize.get().getQuestions();
        int right=0;
        for (Responses res: responses){
            int i=0;
            if(res.getResponse().equals(questions.get(i).getAnswer())){
                right++;

            }

            i++;

        }
        return new  ResponseEntity<>(right,HttpStatus.OK);
    }
}
