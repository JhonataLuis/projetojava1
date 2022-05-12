package projeto.spring.data.aula.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.spring.data.aula.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends JpaRepository<UsuarioSpringData, Long>{

	
	//COMANDOS OTIMIZADOS PARA CONSULTAS NO BANCO DE DADOS
	
	//LISTA DE DADOS CONDICIONAL
	//MOSTRA O USUÁRIO QUE CONTEM O MESMO NOME
	@Query("select p from UsuarioSpringData p where p.nome like %?1%")
	public  List<UsuarioSpringData> buscaPorNome (String nome);
	
	//MOSTRA SOMENTE O VALOR DO PARAMETRO
	//CONSULTA ITEM CONDICIONAL
	@Lock(LockModeType.READ)//ESSA ANOTATION NÃO PERMITE AOS MESMO TEMPO UM USUÁRIO ATUALIZAR UM DADO E O OUTRO PESQISAR / ACONTECE EM SISTEMAS QUE TEM MUITOS USUÁRIO USANDO AO MESMO TEMPO
	@Transactional(readOnly = true)//PODE SER USADO EM SELECT / SOMENTE LEITURA NÃO ALTERA DADOS
	@Query("select p from UsuarioSpringData p where p.nome = :paramnome")
	public UsuarioSpringData buscaPorNomeParam(@Param("paramnome") String paramnome);
	
	
	
	
	default <S extends UsuarioSpringData> S saveAtual(S entity) {
		
		// PROCESSA QUALQUER COISA AQUI DENTRO 
		
		return save(entity);
	}
	
	//DELETE CONDICIONAL
	@Modifying//ANNOTATION SERVE PARA FAZER UMA MODIFICAÇÃO NO BANCO DE DADOS
	@Transactional//ANNOTATION SERVE PARA EXCLUIR E COMMITAR A TRANSAÇÃO
	@Query("delete from UsuarioSpringData u where u.nome = ?1")
	public void deletePorNome(String nome);
	
	//MÉTODO CONDICIONAL PARA ATUALIZAR UM DADO NO BANCO
	@Modifying
	@Transactional
	@Query("update UsuarioSpringData u set u.email = ?1 where u.nome = ?2")
	public void updateEmailPorNome(String email, String nome);
}
