package Entity;

public class Salesperson {

    private String name;
    private String cpf;
    private Double salary;

    public Salesperson() {

    }

    public Salesperson(String name, String cpf, Double salary) {
        this.name = name;
        this.cpf = cpf;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
