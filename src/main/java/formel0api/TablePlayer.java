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
package formel0api;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;

/**
 *
 * @author cgamper
 */
@Entity
@Table( name = "Player")
public class TablePlayer implements Serializable {
    
    private String username;
    private String password;
    private long avatarID;
    
    @Id
    @Column ( name = "username", nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @Column ( name = "password", nullable = false)
    public String getPassword(){
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column ( name = "avatarID", nullable = false)
    public Long getAvatarID() {
        return avatarID;
    }

    public void setAvatarID(Long avatarID) {
        this.avatarID = avatarID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TablePlayer)) {
            return false;
        }
        TablePlayer other = (TablePlayer) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formel0api.TablePlayer[ Username=" + username + ",  Password=" + password + "  Avatar_ID=" + avatarID + "  ]";
    }
    
}
