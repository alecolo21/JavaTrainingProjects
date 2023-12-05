import java.util.*;
public class PersonArrayList {
    public static void main (String[] args)
    { Person Tom = new Person("Tom", 45,"professor");
        Person Harry = new Person("Harry", 20,"student");
        List <Person> tList=new ArrayList<Person> ();
        tList.add(Tom);
        tList.add(Harry);
        System.out.println(Tom);
        System.out.println(Harry);
        System.out.println(tList.size());
    }
}