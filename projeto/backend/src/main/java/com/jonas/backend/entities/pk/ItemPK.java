package com.jonas.backend.entities.pk;

import com.jonas.backend.entities.Compra;
import com.jonas.backend.entities.Livro;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter 
@Setter
public class ItemPK implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn(name = "compra_id")
    private Compra compra;
    
    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.compra);
        hash = 43 * hash + Objects.hashCode(this.livro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemPK other = (ItemPK) obj;
        if (!Objects.equals(this.compra, other.compra)) {
            return false;
        }
        if (!Objects.equals(this.livro, other.livro)) {
            return false;
        }
        return true;
    }

    

   
}
