package tutorial;
 
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
 
public class Main {
    public static void main(String[] args) {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/points.odb");
        EntityManager em = emf.createEntityManager();
 
        boolean allOkay = true;
        try{
        	em.getTransaction().begin();
            for (int i = 0; i < 1000010; i++) {
                Point p = new Point(i, i);
                em.persist(p);
            }
            em.getTransaction().commit();	
        }catch(PersistenceException pe){
        	allOkay = false;
        }finally {
        	em.close();
            emf.close();	
        }
        
        if(allOkay){
        	System.out.println("No problems.");
        }else{
        	System.out.println("Unregistered objectdb activation code");
        }
        
    }
}