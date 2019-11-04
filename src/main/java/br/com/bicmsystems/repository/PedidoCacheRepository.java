package br.com.bicmsystems.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import br.com.bicmsystems.model.PedidoCache;

@Repository
public class PedidoCacheRepository {

	private RestTemplate restTemplate;
	
	@Autowired
	public PedidoCacheRepository(RestTemplateBuilder builder) {
	
		this.restTemplate = builder.build();
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<PedidoCache> listAll(String pv, Integer page, Integer size) {
		
		String url = "http://localhost:8080/pedidos-backend/"+pv+"/false?size="+size+"&page="+page;
		
		ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
		
		if (response.getStatusCode() == HttpStatus.OK) {
			
			return (List<PedidoCache>) response.getBody();
			
		}
		
		return new ArrayList<>();
		
	}
	
}
