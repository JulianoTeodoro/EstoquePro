package estacio.API.EstoquePro.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import estacio.API.EstoquePro.models.Employee;

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
}
