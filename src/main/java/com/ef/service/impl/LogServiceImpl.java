package com.ef.service.impl;

import com.ef.dao.LogDao;
import com.ef.exception.ParserException;
import com.ef.model.Log;
import com.ef.model.LogDto;
import com.ef.service.LogService;
import static com.ef.util.Const.EXCEPTION_IN_DATABASE;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jdiaz86
 */
@Service
public class LogServiceImpl implements LogService {
    
    @Autowired
    LogDao dao;

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
