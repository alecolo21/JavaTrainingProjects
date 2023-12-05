import java.util.*;
public class EmployeeIterator {
    public static void main (String[] args)
    { HashMap<String, Employee> employeeMap = new HashMap<String, Employee>();
        employeeMap.put("emp01", new Employee("emp01", "Tom", "IT"));
        employeeMap.put("emp02", new Employee("emp02", "Jhon", "Supply Chain"));
        employeeMap.put("emp03", new Employee("emp03", "Oliver", "Marketing"));
        employeeMap.put("emp04", new Employee("emp04", "Mary", "IT"));
        Set<Map.Entry<String, Employee>> entrySet = employeeMap.entrySet();
        Iterator<Map.Entry<String, Employee>> iterator = entrySet.iterator();
        System.out.println("Iterate through mappings of HashMap");
        while( iterator.hasNext() ){
            Map.Entry<String, Employee> entry = iterator.next();
            System.out.println( entry.getKey() + " => " + entry.getValue() );
        }
    }
}