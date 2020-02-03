package DB;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//Singleton-Klasse
//Hier kommen die Methoden CRUD, die in der Klasse App aufgerufen werden
public class DbManager {

	private static DbManager singleton_instance;
	
	public static EntityManager instance;
	private EntityManagerFactory f;	
    private EntityManager manager;

    private DbManager() {

    }
		
	public static DbManager getInstance() {
		if (DbManager.singleton_instance == null) {
			DbManager.singleton_instance = new DbManager();
		  }
		    
		return DbManager.singleton_instance;
	}
	
	
	//--------------------------------------------------------Methoden
	public void persist(Object p) {
		  		  
		  f = Persistence.createEntityManagerFactory("MyUnit");
		  manager = f.createEntityManager();
		
	      manager.getTransaction().begin();
	      manager.persist(p);
	      manager.getTransaction().commit();
	}   
	
	public void removeKunde(int id) {
		  Kunde k = manager.find(Kunde.class, id);

		  manager.getTransaction().begin();
		  manager.remove(id);
		  manager.getTransaction().commit();
	}
}
