package com.ef.dao.impl;

import com.ef.dao.AbstractDao;
import com.ef.dao.LogDao;
import com.ef.model.Log;
import com.ef.model.LogDto;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jdiaz86
 */
@Repository("LogDao")
public class LogDaoImpl extends AbstractDao implements LogDao {
    
    private static final String DELETE_FROM = "delete from Log";

    @Override
    public Log findOne(Long id) {
        return (Log) getSession().get(Log.class, id);
    }

    @Override
    public List<Log> findAll() {
        return (List<Log>) getSession().createCriteria(Log.class).list();
    }

    @Override
    public void save(Log log) {
        getSession().beginTransaction();
        getSession().save(log);
        getSession().getTransaction().commit();
    }

    @Override
    public Long update(Log log) {
        getSession().saveOrUpdate(log);
        return log.getId();
    }

    @Override
    public void removeAll() {
        getSession().createQuery(DELETE_FROM).executeUpdate();
    }
    
    @Override
    public void saveBatch(List<Log> logs) {
        getSession().beginTransaction();
        for (int i = 0; i < logs.size(); i++) {
            getSession().save(logs.get(i));
        }
        getSession().getTransaction().commit();
    }
    
    @Override
    public List<LogDto> process(LocalDateTime from, LocalDateTime to, Long threshold) {
        Criteria criteria = getSession().createCriteria(Log.class);
        String hql = "SELECT count(log.id) as times, log.ip as ip FROM Log log WHERE log.date BETWEEN :from AND :to"
                    + " GROUP BY log.ip HAVING count(log.id) > :threshold ORDER BY count(log.id) DESC";
        Query query = getSession().createQuery(hql);
        query.setParameter("from", Date.from(from.atZone(ZoneId.systemDefault()).toInstant()));
        query.setParameter("to", Date.from(to.atZone(ZoneId.systemDefault()).toInstant()));
        query.setParameter("threshold", threshold);
        query.setResultTransformer(Transformers.aliasToBean(LogDto.class));
        return (List<LogDto>) query.list();
    }

}
