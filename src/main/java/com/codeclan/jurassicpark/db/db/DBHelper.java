package com.codeclan.jurassicpark.db.db;

import com.codeclan.jurassicpark.db.models.Carnivore;
import com.codeclan.jurassicpark.db.models.Dinosaur;
import com.codeclan.jurassicpark.db.models.Herbivore;
import com.codeclan.jurassicpark.db.models.Paddock;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private static Transaction transaction;
    private static Session session;

    public static void saveOrUpdate(Object object) {

        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static <T> void deleteAll(Class classType) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            Criteria cr = session.createCriteria(classType);
            List<T> results = cr.list();
            for (T result : results) {
                session.delete(result);
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void delete(Object object){
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

//    getAll
    public static <T> List<T> getAll(Class classType){
        session = HibernateUtil.getSessionFactory().openSession();
        List<T> results = null;
        Criteria criteria = session.createCriteria(classType);
        results = getList(criteria);
        return results;
    }

//    find
    public static <T> T find(Class classType, int id){
        session = HibernateUtil.getSessionFactory().openSession();
        T result = null;
        Criteria criteria = session.createCriteria(classType);
        criteria.add(Restrictions.idEq(id));
        result = getUnique(criteria);
        return result;
    }

//    getPaddockDinosaurs
    public static List<Dinosaur> getPaddocksDinosaurs(Paddock paddock){
        session = HibernateUtil.getSessionFactory().openSession();
        List<Dinosaur> results = null;
        Criteria criteria = session.createCriteria(Dinosaur.class);
        criteria.add(Restrictions.eq("paddock", paddock));
        results = getList(criteria);
        return results;
    }

//    findAvailablePaddocks

//    if both conditions true, add Paddock to list of available
    public static List<Paddock> getAvailablePaddocks(Dinosaur dinosaur){
        List<Paddock> availablePaddocks = new ArrayList<>();
        List<Paddock> allPaddocks = getAll(Paddock.class);
//        should remove dinosaurs current paddock from here

        boolean spaceInPaddock;
        boolean dinoCond;

        for (Paddock paddock : allPaddocks){

            int dinoCount = getPaddocksDinosaurs(paddock).size();
            spaceInPaddock = dinoCount == 0 || dinoCount < paddock.getCapacity();

            Dinosaur firstDino = null;
//            set this if paddock not empty
            if (dinoCount != 0){
              firstDino = getPaddocksDinosaurs(paddock).get(0); // might cause issue
            }

//            set a DINO TYPE condition based upon the Dinosaur we're taking in
            if (dinosaur instanceof Carnivore){
//                if its a Carnivore, check its the same species as the first dino in the Paddock
                dinoCond = dinosaur.getSpecies() == firstDino.getSpecies();
            } else {
//                if its a Herbivore, check the first dino in the Paddock is also a Herbivore
                dinoCond = firstDino instanceof Herbivore;
            }

//            if there is SPACE IN THE PADDOCK and THE DINO TYPE CONDITION IS TRUE
            if (spaceInPaddock && dinoCond){
                availablePaddocks.add(paddock);
            }

        }

        return availablePaddocks;

    }


//    getList
    public static <T> List<T> getList(Criteria criteria){
        List<T> results = null;
        try {
            transaction = session.beginTransaction();
            results = criteria.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return results;
    }

//    getUnique
    public static <T> T getUnique(Criteria criteria){
        T result = null;
        try {
            transaction = session.beginTransaction();
            result = (T)criteria.uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }


}