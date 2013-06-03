/*
 * Copyright 2013 cgamper.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tuwien.big.formel0.picasa;

import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.photos.AlbumEntry;
import com.google.gdata.data.photos.UserFeed;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;


/**
 *
 * @author cgamper
 */

@ManagedBean(name = "drivers")
public class RaceDriverControl implements IRaceDriverService {
    
    List<RaceDriver> list;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public RaceDriverControl() {
        list = new ArrayList<RaceDriver>();
    }
    
    public List<RaceDriver> getRaceDrivers() throws IOException, ServiceException {
        
        System.out.println("GET MY PICS FROM PICASA");
        
        PicasawebService myService = new PicasawebService("lab4");
//        myService.setUserCredentials("christophergamper@gmail.com", "test");
        
//        URL feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/107302466601293793664");
            
        URL feedUrl = new URL("https://picasaweb.google.com/data/feed/api/user/107302466601293793664?kind=album");

        UserFeed myUserFeed = myService.getFeed(feedUrl, UserFeed.class);

        for (AlbumEntry myAlbum : myUserFeed.getAlbumEntries()) {
            System.out.println(myAlbum.getTitle().getPlainText());
        }
        
//        AlbumFeed feed = myService.getFeed(feedUrl, AlbumFeed.class);
//        System.out.println("Empty " + feed.getPhotoEntries().isEmpty());
//        for(PhotoEntry photo : feed.getPhotoEntries()) {
//            System.out.println(photo.getTitle().getPlainText());
//            if(photo.getCategories().contains("Driver")) 
//            {
////                RaceDriver local = new RaceDriver();
////                local.setName(photo.getTitle());
////                local.setWikiUrl(null);
//                System.out.println(photo.toString());
//            }
//        }
//        
        return list;
    }
}
