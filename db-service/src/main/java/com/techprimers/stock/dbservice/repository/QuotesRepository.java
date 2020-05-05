package com.techprimers.stock.dbservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techprimers.stock.dbservice.Moodel.Quote;
@Repository
public interface QuotesRepository extends JpaRepository<Quote,Integer>

{

	List<Quote> findByusername(String username);

}
