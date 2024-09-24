package estacio.API.EstoquePro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
	
	@GetMapping("/api/usuario/teste")
	public String consultar() {
		return "Juliano";
	}
}
