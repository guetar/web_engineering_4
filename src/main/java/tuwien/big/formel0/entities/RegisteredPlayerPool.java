package tuwien.big.formel0.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * Contains all current registered players
 */
@ManagedBean(name = "rpp")
@ApplicationScoped
public class RegisteredPlayerPool {

    ConcurrentMap<String, Player> regplayers = null;

    /**
     * Creates a new instance of RegisteredPlayerPool
     */
    public RegisteredPlayerPool() {
        regplayers = new ConcurrentHashMap<String, Player>();

        //Add test player
        Player tp = new Player();
        tp.setName("t");
        tp.setPassword("t");
        regplayers.put("t", tp);
    }

    public boolean addPlayer(Player p) {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4");
        
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Player tp = new Player();
        tp.setPassword(p.getPassword());
        tp.setName(p.getName());
        tp.setFirstname(p.getFirstname());
        tp.setLastname(p.getLastname());
        tp.setBirthday(p.getBirthday());
        tp.setSex(p.getSex());

        em.persist(tp);

        tx.commit();

        em.close();

        emf.close();

        return true;
        //return regplayers.putIfAbsent(p.getName(), p) == null;
    }

    public Player getRegisteredPlayer(String username, String password) {
       Player curplayer = null;
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin();

        Player tp = em.find(Player.class, username);
        
        if (tp.getPassword().equals(password)) {
                  
            curplayer = tp;
        }
        
        

        tx.commit();

        em.close();

        emf.close();


        return null;
    }

    /**
     * @return the players
     */
    public List<Player> getRegplayers() {
        return new ArrayList<Player>(regplayers.values());
    }
}
