package com.ef.service.impl;

import com.ef.dao.ResultDao;
import com.ef.dao.impl.ResultDaoImpl;
import com.ef.exception.ParserException;
import com.ef.model.Result;
import com.ef.service.ResultService;
import static com.ef.util.Const.EXCEPTION_IN_DATABASE;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author jdiaz86
 */
public class ResultServiceImpl implements ResultService {
    
    ResultDao dao = new ResultDaoImpl();

    @Override
    public Result findOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Result> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void save(Result result) {
        try {
             dao.save(result);
        } catch (HibernateException e) {
            throw new ParserException(EXCEPTION_IN_DATABASE + e.getMessage());
        }
    }

    @Override
    public Long update(Result result) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
