package br.com.bicmsystems.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bicmsystems.model.PedidoCache;
import br.com.bicmsystems.repository.PedidoCacheRepository;

@Service
public class PedidoCacheService {

	@Autowired
	private PedidoCacheRepository repo;
	
	public List<PedidoCache> listPage(String pv, Pageable pageable) {
		
		return repo.listAll(pv, pageable.getPageNumber(), pageable.getPageSize());
		
	}
	
	public List<PedidoCache> listAll(String pv) {
		
		int TOTAL_DATA = 500;
		int MAX_SIZE = 100;
		List<Integer> ids = IntStream.rangeClosed(0, (TOTAL_DATA/MAX_SIZE)-1)
			    .boxed().collect(Collectors.toList());
		
		List<CompletableFuture<List<PedidoCache>>> futures =
                ids.stream()
                          .map(id -> getListPedidoCacheAsync(pv, id, MAX_SIZE))
                          .collect(Collectors.toList());

        List<List<PedidoCache>> result =
                futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList());

        List<PedidoCache> flat = 
        	    result.stream()
        	        .flatMap(List::stream)
        	        .collect(Collectors.toList());
        
//		List<PedidoCache> result =
//                futures.;
		
        return flat;
		
		
	}
	
	CompletableFuture<List<PedidoCache>> getListPedidoCacheAsync(String pv, Integer page, Integer size){

        CompletableFuture<List<PedidoCache>> future = CompletableFuture.supplyAsync(new Supplier<List<PedidoCache>>() {
            @Override
            public List<PedidoCache> get() {
                final List<PedidoCache> list = repo.listAll(pv, page, size);
                return list;
            }
        });

        return future;
    }
	
}
