/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.siacra.daos;

import com.siacra.models.Oferta;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel
 */
@Repository
public class OfertaDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addOferta(Oferta oferta) {
        getSessionFactory().getCurrentSession().save(oferta);
    }

    public void deleteOferta(Oferta oferta) {
        getSessionFactory().getCurrentSession().delete(oferta);
    }

    public void updateOferta(Oferta oferta) {
        getSessionFactory().getCurrentSession().update(oferta);
    }

    public Oferta getOfertaById(Integer id) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Oferta where idOferta=?")
                                            .setParameter(0, id).list();
        return (Oferta)list.get(0);
    }
    
    public List<Oferta> getOfertasByCiclo(Integer idCiclo) {
        List list = getSessionFactory().getCurrentSession().createQuery("from Oferta where idCiclo=?").setParameter(0, idCiclo).list();
        return list;
    }
    
    public boolean getExistOferta(Integer idCiclo,Integer idGrupo,Integer idAcuerdo) {
        List list = getSessionFactory().getCurrentSession()
                                            .createQuery("from Oferta where idCiclo=? and idGrupo=? and idAcuerdo=? ")
                                            .setParameter(0, idCiclo).setParameter(1, idGrupo).setParameter(2, idAcuerdo).list();
        
        if(list.isEmpty()){
            return false;
        }else{
            return true;
        }
    }
    
    public List<Oferta> getOfertas() {
        List list = getSessionFactory().getCurrentSession().createQuery("from Oferta").list();
        return list;
    }
    

}