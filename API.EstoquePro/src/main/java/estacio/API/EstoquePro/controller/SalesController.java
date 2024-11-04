package estacio.API.EstoquePro.controller;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import estacio.API.EstoquePro.DAO.VendasDAO;
import estacio.API.EstoquePro.models.Sales;
import estacio.API.EstoquePro.models.Stock;

@RestController
public class SalesController {
	
    @PostMapping("/venda/registrarVenda")
    public ResponseEntity<String> registrarVenda(@RequestBody Sales sale) {
        try {
            boolean isRegistered = new VendasDAO().registrarVenda(sale);

            if (isRegistered) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Venda registrada com sucesso.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao registrar venda. Estoque insuficiente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no servidor.");
        }
    }

    @GetMapping("/venda/listarVenda")
    public ResponseEntity<List<Sales>> listarTodasVendas() {
        List<Sales> salesList = new VendasDAO().listarVendas();
        return ResponseEntity.ok(salesList);
    }

    @GetMapping("/venda/{employeeId}")
    public ResponseEntity<List<Sales>> listarVendasPorFuncionario(@PathVariable int employeeId) {
        List<Sales> salesList = new VendasDAO().listarVendaPorFuncionario(employeeId);
        return ResponseEntity.ok(salesList);
    }

}
