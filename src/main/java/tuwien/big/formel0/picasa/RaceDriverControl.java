package tuwien.big.formel0.picasa;

import com.google.gdata.client.Query;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumFeed;
import com.google.gdata.data.photos.PhotoEntry;
import com.google.gdata.data.photos.TagEntry;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "drivers")
@ApplicationScoped
public class RaceDriverControl implements IRaceDriverService {

    List<RaceDriver> list;

    @Override
    public List<RaceDriver> getRaceDrivers() throws IOException, ServiceException {
        if(list == null) {
            fetchRaceDrivers();
        }
        
        return list;
    }
    
    private void fetchRaceDrivers() throws IOException, ServiceException {
        list = new ArrayList<RaceDriver>();
        
        PicasawebService myService = new PicasawebService("lab4");
        String userUrl = "https://picasaweb.google.com/data/feed/api/user/107302466601293793664";
        String albumUrl = userUrl + "/albumid/5868849825181458161";
        URL feedUrl = new URL(userUrl);

        Query myQuery = new Query(feedUrl);
        myQuery.setStringCustomParameter("kind", "photo");
        myQuery.setStringCustomParameter("tag", "Driver");

        AlbumFeed driverFeed = myService.query(myQuery, AlbumFeed.class);

        for (PhotoEntry photo : driverFeed.getPhotoEntries()) {
            String photoUrl = albumUrl + "/photoid/" + photo.getId().split("/")[10] + "?kind=tag";
            feedUrl = new URL(photoUrl);
            AlbumFeed tagSearchFeed = myService.query(new Query(feedUrl), AlbumFeed.class);

            String wiki = "";
            
            for (TagEntry tag : tagSearchFeed.getTagEntries()) {
                if (tag.getTitle().getPlainText().startsWith("wiki:")) {
                    wiki = tag.getTitle().getPlainText().substring(5);
                    break;
                }
            }
            
            RaceDriver raceDriver = new RaceDriver();
            raceDriver.setName(photo.getMediaGroup().getDescription().getPlainTextContent());
            raceDriver.setUrl(photo.getMediaThumbnails().get(2).getUrl());
            raceDriver.setWikiUrl(wiki);
            list.add(raceDriver);
        }
    }
}
