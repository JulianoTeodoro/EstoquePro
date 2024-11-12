package EstoquePro.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import EstoquePro.DAO.*;
import EstoquePro.DTO.ClientLoginInputModel;
import EstoquePro.Utils.JwtUtil;
import EstoquePro.models.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/employees")
public class UsuarioController {
	
    @Autowired
    private JwtUtil jwtUtil;

    @Operation(summary = "Listar funcionarios")
	@GetMapping("/api/funcionarios")
	public List<Employee> listarFuncionarios() {
		var conexao = new UsuarioDAO();
		
		var funcionarios = conexao.GetFuncionarios();
		
		return funcionarios;
	}
	
    @Operation(summary = "Realiza o login do funcionário")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ClientLoginInputModel login) {
        Employee employee = new UsuarioDAO().loginEmployee(login.Cpf, login.Senha);

        if (employee != null) {
            String jwtToken = jwtUtil.generateToken(employee.getCpf(), employee.Role);
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwtToken);
            response.put("role", employee.Role);
            response.put("funcionario", employee);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("CPF ou senha incorretos.");
        }
    }
    
    @Operation(summary = "Cadastra um novo funcionário")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Funcionário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
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
