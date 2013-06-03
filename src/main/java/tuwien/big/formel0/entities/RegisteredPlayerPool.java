package tuwien.big.formel0.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import tuwien.big.formel0.picasa.RaceDriver;

/**
 *
 * Contains all current registered players
 */
@ManagedBean(name = "rpp")
@ApplicationScoped
public class RegisteredPlayerPool {
    
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lab4");
    private EntityManager em;

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
        return regplayers.putIfAbsent(p.getName(), p) == null;
    }

    public Player getRegisteredPlayer(String username, String password) {
        Player curplayer;

        if ((curplayer = regplayers.get(username)) != null) {
            if (curplayer.getPassword().equals(password)) {
                return curplayer;
            }
        }

        return null;
    }

    /**
     * @return the players
     */
    public List<Player> getRegplayers() {
        return new ArrayList<Player>(regplayers.values());
    }
    
    public RaceDriver createRaceDriver(String name, String url, String wikiurl) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        RaceDriver raceDriver = new RaceDriver();
        raceDriver.setName(name);
        raceDriver.setUrl(url);
        raceDriver.setWikiUrl(wikiurl);
        em.persist(raceDriver);
        em.getTransaction().commit();
        em.close();
        return raceDriver;
    }
    
    public RaceDriver findRaceDriverByID(int id) {
        em = emf.createEntityManager();
        return em.find(RaceDriver.class, id);
    }
    
    public Player createPlayer(String firstname, String lastname, String name, String password, String birthday, String sex) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        Player player = new Player();
        player.setFirstname(firstname);
        player.setLastname(lastname);
        player.setName(name);
        player.setPassword(password);
        player.setBirthday(birthday);
        player.setSex(sex);
        em.persist(player);
        em.getTransaction().commit();
        em.close();
        return player;
    }

    
    public void removePlayer(int id) {
        em = emf.createEntityManager();
        Player player = findPlayerByID(id);
        if(player != null) {
            em.remove(player);
        }
    }
    
    public void removePlayer(String username) {
        em = emf.createEntityManager();
        Player player = findPlayerByUsername(username);
        if(player != null) {
            em.remove(player);
        }
    }

    
    public Player findPlayerByID(int id) {
        em = emf.createEntityManager();
        return em.find(Player.class, id);
    }

    
    public Player findPlayerByUsername(String username) {
        em = emf.createEntityManager();
        TypedQuery query = em.createQuery("SELECT p FROM Player p WHERE p.name LIKE :playerName", Player.class);
        query.setParameter("playerName", username);
        query.setMaxResults(10);
        return ((Player)query.getResultList().get(0))==null ? null : (Player)query.getResultList().get(0) ;
    }

    
    public Collection<Player> findAllPlayers() {
        em = emf.createEntityManager();
        TypedQuery query = em.createQuery("SELECT p FROM Player p", Player.class);
	return (Collection<Player>) query.getResultList();
    }
    
    public Player checkLogin(String username, String password) {
        Player curplayer;

        if ((curplayer = findPlayerByUsername(username)) != null) {
            if (curplayer.getPassword().equals(password)) {
                return curplayer;
            }
        }

        return null;
    }
}
