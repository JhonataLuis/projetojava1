package projeto.spring.data.aula.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import projeto.spring.data.aula.model.Telefone;

@Repository
public interface InterfaceTelefone extends JpaRepository<Telefone, Long> {

}
