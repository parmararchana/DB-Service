package com.techprimers.stock.dbservice.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techprimers.stock.dbservice.Moodel.Quote;
import com.techprimers.stock.dbservice.model.Quotes;
import com.techprimers.stock.dbservice.repository.QuotesRepository;

@RestController
@RequestMapping("/rest/db")
public class DbServiceResource 
{
	@Autowired
	private QuotesRepository quotesRepository;

	
	@GetMapping("/{username}")
	public List<String> getQuotes(@PathVariable("username")  String username)
	{
		
		return getQuoteByusername(username);								  
		
	}

	
	private List<String> getQuoteByusername(String username) {
		return quotesRepository.findByusername(username).stream()
												  .map(Quote :: getQuote)
												  .collect(Collectors.toList());
	}
	
	
	
	@PostMapping("/delete/{username}")
	public List<String> delete(@PathVariable("username") String username)
	{
		
		List<Quote> quotes=	quotesRepository.findByusername(username);
		quotesRepository.deleteAll(quotes);
		return getQuoteByusername(username);
	}
	
	@PostMapping("/add")
	public List<String> add(@RequestBody Quotes quotes)
	{
		
		quotes.getQuotes()
			  .stream()
			  .map(quote -> new Quote(quotes.getUsername(),quote))
			  .forEach(quote -> quotesRepository.save(quote));
		
		return getQuoteByusername(quotes.getUsername());
	}
	
	
	
}
