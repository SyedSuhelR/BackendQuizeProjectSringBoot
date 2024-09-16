package com.quizeBackend.quizeApp.dao;

import com.quizeBackend.quizeApp.Model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {

    List<Question> findByCategory(String category);

    @Query(value = "SELECT  * FROM question q where q.catergory=:categiry ORDERY BY RANDOM() LIMIT:num",nativeQuery = true)
    List<Question> findRandomQuiestionByCategory(String categiry, int num);
}
