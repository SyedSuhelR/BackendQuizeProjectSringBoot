package com.quizeBackend.quizeApp.dao;

import com.quizeBackend.quizeApp.Model.Quize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizeDtao extends JpaRepository<Quize,Integer> {
}
