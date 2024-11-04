package estacio.API.EstoquePro.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import estacio.API.EstoquePro.models.Sales;

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

            Context.getConexao().commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
        	Context.getConexao().close();
        }
    }
    
    public List<Sales> listarVendas() {
        List<Sales> salesList = new ArrayList<>();
        String sql = "SELECT * FROM estoque.sales";

        try (Statement stmt = Context.getConexao().createStatement();
             ResultSet resultSet = stmt.executeQuery(sql)) {

            while (resultSet.next()) {
                Sales sale = new Sales();
                sale.setId(resultSet.getInt("idsale"));
                sale.setDateSale(resultSet.getDate("date_sale"));
                sale.setClientId(resultSet.getInt("clients_idclient"));
                sale.setClientCpf(resultSet.getString("clients_cpf"));
                sale.setEmployeeId(resultSet.getInt("employee_idemployee"));
                sale.setEmployeeCpf(resultSet.getString("employee_cpf_employee"));
                sale.setProductId(resultSet.getInt("stock_idproduct"));
                sale.setQuantity(resultSet.getInt("quantity"));
                sale.setValor(resultSet.getDouble("valortotal"));
                salesList.add(sale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }

    public List<Sales> listarVendaPorFuncionario(int employeeId) {
        List<Sales> salesList = new ArrayList<>();
        String sql = "SELECT * FROM estoque.sales WHERE employee_idemployee = ?";

        try (PreparedStatement stmt = Context.getConexao().prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            try (ResultSet resultSet = stmt.executeQuery()) {
                while (resultSet.next()) {
                    Sales sale = new Sales();
                    sale.setId(resultSet.getInt("idsale"));
                    sale.setDateSale(resultSet.getDate("date_sale"));
                    sale.setClientId(resultSet.getInt("clients_idclient"));
                    sale.setClientCpf(resultSet.getString("clients_cpf"));
                    sale.setEmployeeId(resultSet.getInt("employee_idemployee"));
                    sale.setEmployeeCpf(resultSet.getString("employee_cpf_employee"));
                    sale.setProductId(resultSet.getInt("stock_idproduct"));
                    sale.setQuantity(resultSet.getInt("quantity"));
                    sale.setValor(resultSet.getDouble("valortotal"));
                    salesList.add(sale);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesList;
    }


}
