package com.ef.dao.impl;

import com.ef.dao.AbstractDao;
import com.ef.dao.ResultDao;
import com.ef.model.Result;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jdiaz86
 */
@Repository("ResultDao")
public class ResultDaoImpl extends AbstractDao implements ResultDao {

    @Override
    public Result findOne(Long id) {
        return (Result) getSession().get(Result.class, id);
    }

    @Override
    public List<Result> findAll() {
        return (List<Result>) getSession().createCriteria(Result.class).list();
    }

    @Override
    public void save(Result result) {
        getSession().beginTransaction();
        getSession().save(result);
        getSession().getTransaction().commit();
    }

    @Override
    public Long update(Result result) {
        getSession().saveOrUpdate(result);
        return result.getId();
    }
    
}
