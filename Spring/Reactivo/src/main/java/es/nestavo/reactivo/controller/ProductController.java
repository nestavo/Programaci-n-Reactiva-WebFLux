package es.nestavo.reactivo.controller;

import es.nestavo.reactivo.modelo.Producto;
import es.nestavo.reactivo.servicio.ProductService;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {
	private final ProductService productService;

	@Autowired
	private KafkaTemplate<String, Map<String, Object>> kafkaTemplate;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/nombre")
	public Flux<String> obtenerNombre() {
		return productService.obtenerNombresDeProductos();
	}

	@GetMapping("/nombreprecio")
    public Flux<String> obtenerNombresPrecios() {
        // Obtener nombres y precios de productos
        Flux<String> nombresYPrecios = productService.obtenerNombresYPreciosDeProductos();

        // Publicar nombres y precios en un topic de Kafka
        nombresYPrecios.subscribe(nombrePrecio -> {
            Map<String, Object> message = new HashMap<>();
            message.put("Nombre y Precio", nombrePrecio);
            kafkaTemplate.send("nombreprecio-topic", message); // Enviar mensaje al topic de Kafka
        });

        return nombresYPrecios;
    }

	@GetMapping
	public Flux<Producto> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("/{id}")
	public Mono<Producto> getProductById(@PathVariable String id) {
		return productService.getProductById(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Producto> createProduct(@RequestBody Producto product) {
		return productService.saveProduct(product);
	}

	@PutMapping("/{id}")
	public Mono<Producto> updateProduct(@PathVariable String id, @RequestBody Producto product) {
		return productService.getProductById(id).flatMap(existingProduct -> {
			existingProduct.setName(product.getName());
			existingProduct.setPrecio(product.getPrecio());
			return productService.saveProduct(existingProduct);
		});
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> deleteProduct(@PathVariable String id) {
		return productService.deleteProduct(id);
	}
}
