package es.nestavo.reactivo.servicio;



import es.nestavo.reactivo.modelo.Producto;
import es.nestavo.reactivo.repository.Repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    private final Repositorio productRepository;
    
	
	

	@KafkaListener(topics = "nombreprecio-topic", groupId = "group-id2")
	public void consumeMessage(String message) {
		System.out.println("Mensaje recibido: " + message);
	}

    public ProductService(Repositorio productRepository) {
        this.productRepository = productRepository;
    }

    public Flux<Producto> getAllProducts() {
        return productRepository.findAll();
    }

    public Mono<Producto> getProductById(String id) {
        return productRepository.findById(id);
    }

    public Mono<Producto> saveProduct(Producto product) {
        return productRepository.save(product);
    }

    public Mono<Void> deleteProduct(String id) {
        return productRepository.deleteById(id);
    }
    public Flux<String> obtenerNombresDeProductos() {
        return productRepository.findAll()
            .map(producto -> producto.getName() + "  ")
            .collectList()
            .map(lista -> String.join(" ", lista))
            .flux();
    }
    public Flux<String> obtenerNombresYPreciosDeProductos() {
        return productRepository.findAll()
            .flatMap(producto -> Flux.just(producto.getName() + "= " + producto.getPrecio() +" "))
            .collectList()
            .map(lista -> String.join(", ", lista))
            .flux();
    }


}
