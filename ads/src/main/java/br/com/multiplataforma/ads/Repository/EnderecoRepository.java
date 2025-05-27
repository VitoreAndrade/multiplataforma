package br.com.multiplataforma.ads.Repository;

import br.com.multiplataforma.ads.Model.Endereco;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EnderecoRepository extends MongoRepository<Endereco, String> {
}
