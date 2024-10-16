package estacio.API.EstoquePro.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import estacio.API.EstoquePro.DAO.UsuarioDAO;
import estacio.API.EstoquePro.models.Employee;

@RestController
public class FuncionarioController {
	
	@GetMapping("/api/funcionarios")
	public List<Employee> listarFuncionarios() {
		var conexao = new UsuarioDAO();
		
		var funcionarios = conexao.GetFuncionarios();
		
		return funcionarios;
	}
}
