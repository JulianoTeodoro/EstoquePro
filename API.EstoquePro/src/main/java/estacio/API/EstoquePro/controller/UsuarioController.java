package estacio.API.EstoquePro.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import estacio.API.EstoquePro.DAO.*;
import estacio.API.EstoquePro.DTO.ClientLoginInputModel;
import estacio.API.EstoquePro.models.*;

@RestController
public class UsuarioController {
	
	@GetMapping("/api/funcionarios")
	public List<Employee> listarFuncionarios() {
		var conexao = new UsuarioDAO();
		
		var funcionarios = conexao.GetFuncionarios();
		
		return funcionarios;
	}
	
    @PostMapping("/funcionarios/login")
    public ResponseEntity<?> login(@RequestBody ClientLoginInputModel login) {
        Employee employee = new UsuarioDAO().loginEmployee(login.Cpf, login.Senha);

        if (employee != null) {
            // Sucesso no login, retorna os dados do funcionário (exceto a senha)
            return ResponseEntity.ok(employee);
        } else {
            // Credenciais inválidas
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("CPF ou senha incorretos.");
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Employee employee) {
        boolean isRegistered = new UsuarioDAO().registerEmployee(employee);

        if (isRegistered) {
            // Sucesso no cadastro
            return ResponseEntity.status(HttpStatus.CREATED).body("Funcionário cadastrado com sucesso.");
        } else {
            // Falha no cadastro
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao cadastrar o funcionário.");
        }
    }


	
}
