package br.com.bicmsystems.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "PedidoCache")
public class PedidoCache {
	
	@Id
	private String id;

	private String nome;
	
	private LocalDateTime dataCadastro;
	
	private String pv;
	
}
