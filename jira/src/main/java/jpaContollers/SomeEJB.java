/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpaContollers;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Oana
 */
@Stateless
public class SomeEJB {

    @PersistenceContext
    private EntityManager entityManager;

    public void something() {
        System.out.println("from some ejb");
    }
}
