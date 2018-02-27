package com.ef.service.impl;

import com.ef.dao.LogDao;
import com.ef.dao.impl.LogDaoImpl;
import com.ef.exception.ParserException;
import com.ef.model.Log;
import com.ef.model.LogDto;
import com.ef.service.LogService;
import static com.ef.util.Const.EXCEPTION_IN_DATABASE;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.HibernateException;

/**
 *
 * @author jdiaz86
 */
public class LogServiceImpl implements LogService {
    
    LogDao dao = new LogDaoImpl();

    @Override
    public Log findOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Log> findAll() {
        try {
             return dao.findAll();
        } catch (HibernateException e) {
            throw new ParserException(EXCEPTION_IN_DATABASE + e.getMessage());
        }
    }
    
    @Override
    public void save(Log log) {
        try {
             dao.save(log);
        } catch (HibernateException e) {
            throw new ParserException(EXCEPTION_IN_DATABASE + e.getMessage());
        }
    }

    @Override
    public Long update(Log log) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void printVersion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeAll() {
        try {
             dao.removeAll();
        } catch (HibernateException e) {
            throw new ParserException(EXCEPTION_IN_DATABASE + e.getMessage());
        }
    }

    @Override
    public void saveBatch(List<Log> logs) {
        try {
             dao.saveBatch(logs);
        } catch (HibernateException e) {
            throw new ParserException(EXCEPTION_IN_DATABASE + e.getMessage());
        }
    }

    @Override
    public List<LogDto> process(LocalDateTime from, LocalDateTime to, Long threshold) {
        try {
             return dao.process(from, to, threshold);
        } catch (HibernateException e) {
            throw new ParserException(EXCEPTION_IN_DATABASE + e.getMessage());
        }
    }
    
}
