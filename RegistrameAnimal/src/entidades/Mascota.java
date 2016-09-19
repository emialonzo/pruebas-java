/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author emi
 */
@Entity
public class Mascota implements Serializable {

    @ManyToMany
    private List<Persona> personass;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    
    
    
    
    
    
    @ManyToOne
    private Persona registradoPor;
    
    @ManyToMany(mappedBy = "mascotasSeguidas")
    private Collection<Persona> seguidores;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Mascota)) {
            return false;
        }
        Mascota other = (Mascota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Persona getRegistradoPor() {
        return registradoPor;
    }

    public void setRegistradoPor(Persona registradoPor) {
        this.registradoPor = registradoPor;
    }

    public Collection<Persona> getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(Collection<Persona> seguidores) {
        this.seguidores = seguidores;
    }
    
    

    @Override
    public String toString() {
        return "entidades.Mascota[ id=" + id + " ]";
    }
    
}
