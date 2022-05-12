package projeto.spring.data.aula;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;
import projeto.spring.data.aula.dao.InterfaceTelefone;
import projeto.spring.data.aula.model.Telefone;
import projeto.spring.data.aula.model.UsuarioSpringData;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:META-INF/spring-config.xml"})
public class AppSpringDataTest {

	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;
	
	//MÉTODO PARA CADASTRAR UM USUÁRIO NO BANCO DE DADOS
	@Test
	public void testeInsert() {
		System.out.println("Iniciou Spring Data Jpa com sucesso!");
		
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		
		usuarioSpringData.setNome("Rayssa Luis");
		usuarioSpringData.setEmail("rayssa.luis@gmail.com");
		usuarioSpringData.setLogin("rayssa");
		usuarioSpringData.setSenha("54321");
		usuarioSpringData.setIdade(989);
		
		interfaceSpringDataUser.save(usuarioSpringData);
		
		System.out.println("Usuários cadastrados: " +interfaceSpringDataUser.count());
	
	}
	
	//MÉTODO PARA BUSCAR UM DADO NO BANCO DE DADOS PELO ID
	@Test
	public void testeConsulta() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(1L);
		
		System.out.println("Id: " +usuarioSpringData.get().getId());
		System.out.println("Nome: " +usuarioSpringData.get().getNome());
		System.out.println("Email: " +usuarioSpringData.get().getEmail());
		System.out.println("Idade: " +usuarioSpringData.get().getIdade());
		System.out.println("Login: " +usuarioSpringData.get().getLogin());
		System.out.println("Password: " +usuarioSpringData.get().getSenha());
		
		
		for(Telefone telefone : usuarioSpringData.get().getTelefones()) {
			
			System.out.println(telefone.getTipo());
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getUsuarioSpringData().getNome());
			System.out.println("---------------------------------");
		}
		
	}
	
	//MÉTODO PARA LISTAR TODOS OS USUÁRIOS DO BANCO DE DADOS
	@Test
	public void testeConsultaTodos() {
		
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		
		for(UsuarioSpringData usuarioSpringData : lista) {
			
			System.out.println("Id: " +usuarioSpringData.getId());
			System.out.println("Nome: " +usuarioSpringData.getNome());
			System.out.println("Email: " +usuarioSpringData.getEmail());
			System.out.println("Login: " +usuarioSpringData.getLogin());
			System.out.println("Idade: " +usuarioSpringData.getIdade());
			System.out.println("----------------------------------------");
		}
		
	}
	
	//MÉTODO PARA ATUALIZAR UM DADO NO BANCO DE DADOS COM SPRING 
	@Test
	public void testeUpdate() {
		
		Optional<UsuarioSpringData> update = interfaceSpringDataUser.findById(1L);
		
		UsuarioSpringData data = update.get();
		
		data.setNome("Jhonata Luis - Java Full Stack Spring Data JPA");
		data.setIdade(10456);
		data.setEmail("jhonataisluis23@gmail.com");
		
		interfaceSpringDataUser.save(data);
		
		if(data != null) {
			
			System.out.println("Usuário Atualizado no Banco de dados!");
			
		}else{
			System.out.println("Falha ao atualizar!");
		}
	}
	
	//MÉTODO PARA DELETAR UM USUÁRIO DO BANCO DE DADOS
	@Test
	public void testeDelete() {
		
		//DESSA FORMA DELETA DIRETO EM UMA LINHA DE COMANDO
		//interfaceSpringDataUser.deleteById(5L);
		
		//DESSA FORMA CONSULTA O ITEM NO BANCO DE DADOS E DEPOIS DELETA
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(5L);
		
		interfaceSpringDataUser.delete(usuarioSpringData.get());
	}
	
	//MÉTOD PARA MOSTRAR UM PARAMETRO IGUAL
	@Test
	public void testeConsultaNome() {
		
		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("Jhonata");
		
		for(UsuarioSpringData usuarioSpringData : list) {
			
			System.out.println("Nome: " +usuarioSpringData.getNome());
			System.out.println("Login: " +usuarioSpringData.getLogin());
			System.out.println("Email: " +usuarioSpringData.getEmail());
			System.out.println("Idade: " +usuarioSpringData.getIdade());
			System.out.println("Password: " +usuarioSpringData.getSenha());
			System.out.println("------------------------------------------------------");
		}
	}
	
	//METODO DE DELETE COM CONDICIONAL
	@Test
	public void testeConsultaNomeParam() {
		
		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("Angela Maria da Silva");
		
		if(usuarioSpringData != null) {
			
			System.out.println("Usuario: " +usuarioSpringData.getNome());
			
		}else {
			System.out.println("Usuárion não existe na base de dados!");
		}
	}
	
	//MÉTODO TESTE PARA EXCLUIR UM DADO DO BANCO DE FORMA CONDICIONAL
	@Test
	public void testeDeletePorNome() {
		
		interfaceSpringDataUser.deletePorNome("Jhonata Irineu da Silva");
		
	
	}
	
	
	@Test
	public void testeUpdateEmailPorNome() {
		
		interfaceSpringDataUser.updateEmailPorNome("", "Jhonata");
	}
	
	
	
	//TESTE DE TELEFONE =======================================================
	
	//MÉTODO PARA SALVAR O TELEFONE DO USUÁRIO
	@Test
	public void testeInsertTelefone() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(1L);
		
		Telefone telefone = new Telefone();
		
		telefone.setTipo("Casa");
		telefone.setNumero("4178965412");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		
		interfaceTelefone.save(telefone);
	
	}
	
	
	
}
