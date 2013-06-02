package tuwien.big.formel0.picasa;

import javax.persistence.*;
/**
 * Represents a race driver from Picasa
 * @author pl
 *
 */
@Entity
@Table
public class RaceDriver {

    private String name;
    private String url;
    private String wikiUrl;

    public RaceDriver() {
    }

    @Id
    @Column (nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column (nullable = false)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column (nullable = false)
    public String getWikiUrl() {
        return wikiUrl;
    }

    public void setWikiUrl(String wikiUrl) {
        this.wikiUrl = wikiUrl;
    }   

}
