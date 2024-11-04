package estacio.API.EstoquePro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import estacio.API.EstoquePro.DAO.*;
import estacio.API.EstoquePro.DTO.ClientLoginInputModel;
import estacio.API.EstoquePro.models.*;

@RestController
public class ClienteController {
    @PostMapping("/cliente/login")
    public ResponseEntity<?> login(@RequestBody ClientLoginInputModel input) {
        Cliente client = new UsuarioDAO().loginClient(input.Email, input.Senha);

        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("CPF ou senha incorretos.");
        }
    }

    @PostMapping("/cliente/register")
    public ResponseEntity<?> register(@RequestBody Cliente client) {
        boolean isRegistered = new UsuarioDAO().registerClients(client);

        if (isRegistered) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Cliente cadastrado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao cadastrar o cliente.");
        }
    }

}
