public class Person {
    String name;
    int age;
    String type;
    public Person (String name, int age, String type) {
        this.name=name;
        this.age=age;
        this.type=type;
    }

    public String toString( ){
        return this.name+" "+this.age+" "+this.type;}
}