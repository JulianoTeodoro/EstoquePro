package estacio.API.EstoquePro.models;

public class Employee {
	
	public Employee(int idemployee, String cpf_employee, String name_employee , 
			String address_employee, String phone_employee, String role_employee) {
		IdEmployee = idemployee;
		Cpf = cpf_employee;
		Nome = name_employee;
		Address = address_employee;
		Phone = phone_employee;
		Role = role_employee;
	}
	
	public Employee() {
	}

	public int IdEmployee;
    public String Cpf;
	public String Nome;
	public String Address;
	public String Phone;
	public String Role;
	public String Senha;
	
    public int getId() {
        return IdEmployee;
    }

    public void setId(int id) {
        this.IdEmployee = id;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        this.Cpf = cpf;
    }

    public String getName() {
        return Nome;
    }

    public void setName(String name) {
        this.Nome = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        this.Role = role;
    }

}
