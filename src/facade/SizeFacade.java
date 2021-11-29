/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Size;
import javax.persistence.EntityManager;
import tools.Singleton;

/**
 *
 * @author pupil
 */
public class SizeFacade extends AbstractFacade<Size>{
    private EntityManager em;

    public SizeFacade(Class<Size> entityClass) {
        super(entityClass);
        init();
    }
    
    private void init() {
        Singleton singleton = Singleton.getInstance();
        em = singleton.getEntityManager();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
