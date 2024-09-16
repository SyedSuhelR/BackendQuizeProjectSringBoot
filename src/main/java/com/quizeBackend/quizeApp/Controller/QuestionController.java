package com.quizeBackend.quizeApp.Controller;

import com.quizeBackend.quizeApp.Model.Question;
import com.quizeBackend.quizeApp.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){

        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){


        return questionService.getQuestionByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return  questionService.addQuestion(question);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBuId(@PathVariable Integer id){
        return questionService.deleteById(id);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateById( @PathVariable Integer id,Question question){

        return questionService.updateById(id,question);
    }
}
