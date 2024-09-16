package com.quizeBackend.quizeApp.Service;

import com.quizeBackend.quizeApp.Model.Question;
import com.quizeBackend.quizeApp.dao.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {


    @Autowired
    QuestionDao questionDao;
    public ResponseEntity< List<Question>> getAllQuestions() {

        try{
            return new  ResponseEntity(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
        }
        return new  ResponseEntity(new ArrayList<>(), HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity< String> addQuestion(Question question) {

        try {
            questionDao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Not Added",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> deleteById(Integer id) {

        try {
            questionDao.deleteById(id);
            return new ResponseEntity<>("successfully Deleted", HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>("Not Deleted",HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<String> updateById(Integer id, Question question) {
        if(questionDao.existsById(id)){
            question.setId(id);

            questionDao.save(question);
            return new ResponseEntity<>("Record Updated successfully",HttpStatus.OK);
        }
        else
        return new  ResponseEntity(new ArrayList<>(), HttpStatus.NOT_FOUND);
    }
}
