package es.nestavo.reactivo.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;



@EnableReactiveMongoRepositories

@Data
@Document(collection = "producto")
public class Producto {

	@Id
	private String id;
	
	private String name;
	
	private double precio;

}
