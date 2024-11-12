package EstoquePro.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import EstoquePro.models.Employee;
import EstoquePro.models.Stock;

public class EstoqueDAO {
	
	public List<Stock> listAllStock() {
        List<Stock> stockList = new ArrayList<>();
        String sql = "SELECT idproduct, name_product, price_unitary, quantity, price_box, status_product FROM estoque.stock";

        try (
    		PreparedStatement ps = Context.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

                
    		) {
            while (rs.next()) {
                Stock stock = new Stock();
                stock.setId(rs.getInt("idproduct"));
                stock.setName(rs.getString("name_product"));
                stock.setPriceUnitary(rs.getFloat("price_unitary"));
                stock.setQuantity(rs.getInt("quantity"));
                stock.setPriceBox(rs.getFloat("price_box"));
                stock.setStatus(rs.getBoolean("status_product"));
                stockList.add(stock);
            }


    		}
    		catch (SQLException e) {
                throw new RuntimeException(e);
            }
    		
    		return stockList;
    }

    public boolean insertStock(Stock stock) {
        String sql = "INSERT INTO estoque.stock (name_product, price_unitary, quantity, status_product) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = Context.getConexao().prepareStatement(sql)) {
            stmt.setString(1, stock.getName());
            stmt.setFloat(2, stock.getPriceUnitary());
            stmt.setInt(3, stock.getQuantity());
            stmt.setBoolean(4, stock.isStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateStock(Stock stock) {
        String sql = "UPDATE estoque.stock SET name_product = ?, price_unitary = ?, quantity = ?, status_product = ? WHERE idproduct = ?";

        try (PreparedStatement stmt = Context.getConexao().prepareStatement(sql)) {
            stmt.setString(1, stock.getName());
            stmt.setFloat(2, stock.getPriceUnitary());
            stmt.setInt(3, stock.getQuantity());
            stmt.setBoolean(4, stock.isStatus());
            stmt.setInt(5, stock.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStock(int idProduct) {
        String sql = "DELETE FROM estoque.stock WHERE idproduct = ?";

        try (PreparedStatement stmt = Context.getConexao().prepareStatement(sql)) {
            stmt.setInt(1, idProduct);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
