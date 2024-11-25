package EstoquePro.controller;
import java.sql.SQLException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import EstoquePro.DAO.VendasDAO;
import EstoquePro.DTO.SalesViewModel;
import EstoquePro.models.Sales;
import EstoquePro.models.Stock;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@PreAuthorize("hasRole('Almoxarifado')")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/venda")
public class SalesController {
	
	
    @Operation(summary = "Registra uma nova venda e atualiza o estoque")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Venda registrada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/registrarVenda")
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

    @Operation(summary = "Lista todas as vendas")
    @GetMapping("/listarVenda")
    public ResponseEntity<List<SalesViewModel>> listarTodasVendas() {
        List<SalesViewModel> salesList = new VendasDAO().listarVendas();
        return ResponseEntity.ok(salesList);
    }

    @Operation(summary = "Lista vendas por funcionário")
    @GetMapping("/{cpf}")
    public ResponseEntity<List<SalesViewModel>> listarVendasPorFuncionario(@PathVariable String cpf) {
        List<SalesViewModel> salesList = new VendasDAO().listarVendaPorFuncionario(cpf);
        return ResponseEntity.ok(salesList);
    }

}
