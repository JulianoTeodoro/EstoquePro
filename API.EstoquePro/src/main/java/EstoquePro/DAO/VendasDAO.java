package EstoquePro.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import EstoquePro.DTO.SalesViewModel;
import EstoquePro.models.Sales;

public class VendasDAO {
    public boolean registrarVenda(Sales sale) throws SQLException {
        String sqlSale = "INSERT INTO estoque.sales (date_sale, clients_idclient, clients_cpf, employee_idemployee, employee_cpf_employee, stock_idproduct, quantity, valortotal) "
        		+ "SELECT ?, ?, ?, ?, ?, ?, ?, price_unitary * ? FROM estoque.stock where idproduct = ? ";
        String sqlStockUpdate = "UPDATE estoque.stock SET quantity = quantity - ? WHERE idproduct = ? AND quantity > 0";

        try {

            try (PreparedStatement stmtSale = Context.getConexao().prepareStatement(sqlSale)) {
                stmtSale.setDate(1, new java.sql.Date(sale.getDateSale().getTime()));
                stmtSale.setInt(2, sale.getClientId());
                stmtSale.setString(3, sale.getClientCpf());
                stmtSale.setInt(4, sale.getEmployeeId());
                stmtSale.setString(5, sale.getEmployeeCpf());
                stmtSale.setInt(6, sale.getProductId());
                stmtSale.setInt(7, sale.getQuantity());
                stmtSale.setInt(8, sale.getQuantity());
                stmtSale.setInt(9, sale.getProductId());

                stmtSale.executeUpdate();
            }

            try (PreparedStatement stmtStock = Context.getConexao().prepareStatement(sqlStockUpdate)) {
                stmtStock.setInt(1, sale.getQuantity());
                stmtStock.setInt(2, sale.getProductId());

                int rowsAffected = stmtStock.executeUpdate();

                if (rowsAffected == 0) {
                    return false;
                }
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
        	Context.getConexao().close();
        }
    }
    
    public List<SalesViewModel> listarVendas() {
        List<SalesViewModel> salesList = new ArrayList<>();
        String sql = "SELECT * FROM estoque.sales SA "
        		+ "INNER JOIN estoque.stock ST on SA.stock_idproduct = ST.idproduct ";

        try (Statement stmt = Context.getConexao().createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
            	SalesViewModel sale = new SalesViewModel();
                sale.setId(resultSet.getInt("idsale"));
                sale.setDateSale(resultSet.getDate("date_sale"));
                sale.setClientId(resultSet.getInt("clients_idclient"));
                sale.setClientCpf(resultSet.getString("clients_cpf"));
                sale.setEmployeeId(resultSet.getInt("employee_idemployee"));
                sale.setEmployeeCpf(resultSet.getString("employee_cpf_employee"));
                sale.setProductId(resultSet.getInt("stock_idproduct"));
                sale.setQuantity(resultSet.getInt("quantity"));
                sale.setValordaVenda(resultSet.getDouble("valortotal"));
                sale.nomeProduto = resultSet.getString("name_product");
                sale.valorProduto = resultSet.getDouble("price_unitary");
                salesList.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }

    public List<SalesViewModel> listarVendaPorFuncionario(String cpf) {
        List<SalesViewModel> salesList = new ArrayList<>();
        String sql = "SELECT * FROM estoque.sales SA "
        		+ " INNER JOIN estoque.stock ST on SA.stock_idproduct = ST.idproduct "
        		+ " WHERE employee_cpf_employee = ?";

        try (PreparedStatement stmt = Context.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cpf);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                	SalesViewModel sale = new SalesViewModel();
                    sale.setId(resultSet.getInt("idsale"));
                    sale.setDateSale(resultSet.getDate("date_sale"));
                    sale.setClientId(resultSet.getInt("clients_idclient"));
                    sale.setClientCpf(resultSet.getString("clients_cpf"));
                    sale.setEmployeeId(resultSet.getInt("employee_idemployee"));
                    sale.setEmployeeCpf(resultSet.getString("employee_cpf_employee"));
                    sale.setProductId(resultSet.getInt("idproduct"));
                    sale.setQuantity(resultSet.getInt("quantity"));
                    sale.setValordaVenda(resultSet.getDouble("valortotal"));
                    sale.nomeProduto = resultSet.getString("name_product");
                    sale.valorProduto = resultSet.getDouble("price_unitary");
                    salesList.add(sale);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }


}
