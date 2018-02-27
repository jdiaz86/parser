package com.ef.dao;

import com.ef.util.HibernateUtil;
import static com.ef.util.LogUtil.info;
import org.hibernate.Session;

/**
 * common methods for all Dao classes
 * @author jdiaz86
 */
public abstract class AbstractDao {
    
    private static final String SELECT_VERSION = "select version()";
    private Session session;
    
    /**
     * session from session factory
     * @return session 
     */
    public Session getSession() {
        if (session == null) {
            session = HibernateUtil.getSessionFactory().openSession();            
        }
        return session;
    }
    
    /**
     * prints version() commmand from mysql database to test connection
     */
    public void printVersion() {
        String version = (String) getSession().createSQLQuery(SELECT_VERSION).uniqueResult();
        info("*** version *** ", version);
    }
    
    
}