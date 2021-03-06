package tuwien.big.formel0.picasa;

import java.io.Serializable;
import javax.persistence.*;
import tuwien.big.formel0.entities.Player;
/**
 * Represents a race driver from Picasa
 * @author pl
 *
 */
@Entity
@Table(name="Avatar")
public class RaceDriver implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String url;
    private String wikiUrl;

    @JoinColumn
    @ManyToOne
    private Player player;
    
    public RaceDriver() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public Player getPlayer() {
        return player;
    }

}
