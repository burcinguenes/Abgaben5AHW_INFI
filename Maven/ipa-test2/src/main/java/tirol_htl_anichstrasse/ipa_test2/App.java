package tirol_htl_anichstrasse.ipa_test2;

import javax.persistence.*;
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Person p = new Person();
        p.setAge(18);
        p.setFirstName("Seppl");
        p.setLastName("Huberer");
        
        
        EntityManagerFactory f = Persistence.createEntityManagerFactory("MyUnit");
        EntityManager manager = f.createEntityManager();
        manager.getTransaction().begin();
        manager.persist(p);
        manager.getTransaction().commit();
        
       
    }
}
