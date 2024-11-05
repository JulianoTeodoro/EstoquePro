package estacio.API.EstoquePro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import estacio.API.EstoquePro.DAO.EstoqueDAO;
import estacio.API.EstoquePro.Utils.JwtUtil;
import estacio.API.EstoquePro.models.Stock;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
//@PreAuthorize("hasRole('Almoxarifado')")
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/estoque")
public class EstoqueController {
	

    @Operation(summary = "Lista todos os itens do estoque")
    @GetMapping("/listar")
    public ResponseEntity<List<Stock>> getAllEstoque() {
        List<Stock> stockList = new EstoqueDAO().listAllStock();
        return ResponseEntity.ok(stockList);
    }

    @Operation(summary = "Adiciona um novo item ao estoque")
    @PostMapping("/adicionar")
    public ResponseEntity<String> addStock(@RequestBody Stock stock) {
        boolean isAdded =  new EstoqueDAO().insertStock(stock);

        if (isAdded) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Produto adicionado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao adicionar o produto.");
        }
    }

    @Operation(summary = "Atualiza um item do estoque")
    @PutMapping("/atualizar")
    public ResponseEntity<String> updateStock(@RequestBody Stock stock) {
        boolean isUpdated = new EstoqueDAO().updateStock(stock);

        if (isUpdated) {
            return ResponseEntity.ok("Produto atualizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao atualizar o produto.");
        }
    }

    @Operation(summary = "Deleta um item do estoque")
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable int id) {
        boolean isDeleted =  new EstoqueDAO().deleteStock(id);

        if (isDeleted) {
            return ResponseEntity.ok("Produto deletado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao deletar o produto.");
        }
    }

}
