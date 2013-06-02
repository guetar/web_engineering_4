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
package tuwien.big.formel0.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author cgamper
 */
@Entity
@Table(name="Avatar")
public class Avatar implements Serializable {
    
    private Long id;
    private String link;
    
    @Id
    @Column( name = "id", nullable=false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Column ( name = "link", nullable=false )
    public String getLink() {
        return link;
    }
    
    public void setLink(String str) {
        this.link=str;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Avatar)) {
            return false;
        }
        Avatar other = (Avatar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "formel0api.Avatar[ id=" + id + " ]";
    }
    
}
