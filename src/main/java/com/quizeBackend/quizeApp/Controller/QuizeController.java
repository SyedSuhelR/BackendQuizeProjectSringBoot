package com.quizeBackend.quizeApp.Controller;



import com.quizeBackend.quizeApp.Model.Question;
import com.quizeBackend.quizeApp.Model.QuestionWrapper;
import com.quizeBackend.quizeApp.Model.Responses;
import com.quizeBackend.quizeApp.Service.QuizeService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quize")
public class QuizeController {

    @Autowired
    QuizeService quizeservice;
    @PostMapping("create")
    public ResponseEntity<String> createQuize(@RequestParam String categiry,@RequestParam int num,@RequestParam String title){

        return quizeservice.createQuize(categiry,num,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizeQuestion(@PathVariable Integer id){

        return quizeservice.getQuizeQuestionById(id);
    }

    @PostMapping("/result{id}")
    public ResponseEntity<Integer> callculateResult(@PathVariable Integer id,@RequestParam List<Responses> responses){

        return quizeservice.calculate(id,responses);
    }

}



