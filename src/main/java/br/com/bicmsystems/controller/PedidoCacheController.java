package br.com.bicmsystems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bicmsystems.model.PedidoCache;
import br.com.bicmsystems.service.PedidoCacheService;

@RestController
@RequestMapping("/pedido-proxy")
public class PedidoCacheController {

	@Autowired
	public PedidoCacheService service;
	
	@GetMapping("/{pv}/{force}")
	public List<PedidoCache> listPage(
			@PathVariable("pv") String pv,
			@PathVariable("force") Boolean force,
			Pageable pageable) {
		
		return service.listPage(pv, pageable);
		
	}
	
	@GetMapping("/{pv}/all")
	public List<PedidoCache> listAll(
			@PathVariable("pv") String pv) {
		
		return service.listAll(pv);
		
	}
	
}
