package estacio.API.EstoquePro.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import estacio.API.EstoquePro.DAO.*;
import estacio.API.EstoquePro.DTO.ClientLoginInputModel;
import estacio.API.EstoquePro.Utils.JwtUtil;
import estacio.API.EstoquePro.models.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	

    @Autowired
    private JwtUtil jwtUtil;
    
    @Operation(summary = "Realiza o login do cliente")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody ClientLoginInputModel input) {
        Cliente client = new UsuarioDAO().loginClient(input.Email, input.Senha);

        
        if (client != null) {
        	String jwtToken = jwtUtil.generateToken(client.getCpf(), "Cliente");
            Map<String, Object> response = new HashMap<>();
            response.put("token", jwtToken);
            response.put("role", "Cliente");
            response.put("cliente", client);
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("CPF ou senha incorretos.");
        }
    }

    @Operation(summary = "Cadastra um novo cliente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Cliente client) {
        boolean isRegistered = new UsuarioDAO().registerClients(client);

        if (isRegistered) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao cadastrar o cliente.");
        }
    }

}
