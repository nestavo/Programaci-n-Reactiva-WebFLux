package es.nestavo.reactivo.repository;



import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import es.nestavo.reactivo.modelo.Producto;

@Repository
public interface Repositorio extends ReactiveMongoRepository<Producto, String> {
 
}

