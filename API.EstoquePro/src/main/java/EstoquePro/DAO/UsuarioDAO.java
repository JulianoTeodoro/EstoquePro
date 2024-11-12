package EstoquePro.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import EstoquePro.models.Cliente;
import EstoquePro.models.Employee;

public class UsuarioDAO {
	public List<Employee> GetFuncionarios() {
		String sql = "SELECT * FROM estoque.employee";
		
		
        List<Employee> funcionarios = new ArrayList<>();

		try (
			PreparedStatement ps = Context.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            
		) {
			while (rs.next()) {
                // Adiciona cada pessoa à lista.
				funcionarios.add(new Employee(rs.getInt("idemployee"), 
						rs.getString("cpf_employee"), rs.getString("name_employee"), 
						rs.getString("address_employee"), rs.getString("phone_employee"), 
						rs.getString("role_employee")));
            }

		}
		catch (SQLException e) {
            // Lança uma exceção em caso de erro.
            throw new RuntimeException(e);
        }
		
		return funcionarios;
	}
	
    public boolean registerEmployee(Employee user) {
        String sql = "INSERT INTO estoque.employee (cpf_employee, name_employee, address_employee, phone_employee, "
        		+ "role_employee) VALUES (?, ?, ?, ?, 'Almoxarifado')";

        try (PreparedStatement stmt = Context.getConexao().prepareStatement(sql)) {
            stmt.setString(1, user.Cpf);
            stmt.setString(2, user.Nome);
            stmt.setString(3, user.Address);
            stmt.setString(4, user.Phone);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean registerClients(Cliente client) {
        String sql = "INSERT INTO estoque.clients (cpf, name_client, address, phone_contact, "
        		+ "email, senha) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = Context.getConexao().prepareStatement(sql)) {
            stmt.setString(1, client.getCpf());
            stmt.setString(2, client.getName());
            stmt.setString(3, client.getAddress());
            stmt.setString(4, client.getPhone());
            stmt.setString(5, client.getEmail());
            stmt.setString(6, client.getSenha());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Método para autenticar o funcionário
    public Employee loginEmployee(String cpf, String senha) {
        String sql = "SELECT * FROM estoque.employee WHERE cpf_employee = ? AND senha = ?";

        try (PreparedStatement stmt = Context.getConexao().prepareStatement(sql)) {
            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setId(resultSet.getInt("idemployee"));
                    employee.setCpf(resultSet.getString("cpf_employee"));
                    employee.setName(resultSet.getString("name_employee"));
                    employee.setAddress(resultSet.getString("address_employee"));
                    employee.setPhone(resultSet.getString("phone_employee"));
                    employee.setRole(resultSet.getString("role_employee"));
                    return employee;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
    
    public Cliente loginClient(String email, String senha) {
        String sql = "SELECT * FROM estoque.clients WHERE email = ? AND senha = ?";

        try (PreparedStatement stmt = Context.getConexao().prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);

            try (ResultSet resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    Cliente client = new Cliente();
                    client.setId(resultSet.getInt("idclient"));
                    client.setCpf(resultSet.getString("cpf"));
                    client.setName(resultSet.getString("name_client"));
                    client.setAddress(resultSet.getString("address"));
                    client.setPhone(resultSet.getString("phone_contact"));
                    client.setEmail(resultSet.getString("email"));
                    return client;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    


}
