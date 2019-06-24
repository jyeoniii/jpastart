package com.example.jpa.service;

import com.example.jpa.EMF;
import com.example.jpa.model.City;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class CityService {

    public void persistCity() {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();

            City city = new City();
            em.persist(city);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }

    }
}
