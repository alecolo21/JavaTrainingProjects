public class Employee{
    private String id;
    private String name;
    private String department;
    public Employee(String id, String name, String department){
        this.id = id;
        this.name = name;
        this.department = department;
    }
    public String toString(){
        return "[" + this.id + " : " + this.name + " : " + this.department +"]";}}