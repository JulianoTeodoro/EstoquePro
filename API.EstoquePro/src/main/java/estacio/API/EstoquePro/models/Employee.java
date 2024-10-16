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

	public int IdEmployee;
    public String Cpf;
	public String Nome;
	public String Address;
	public String Phone;
	public String Role;
}
