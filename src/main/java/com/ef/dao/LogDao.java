package com.ef.dao;

import com.ef.model.Log;
import com.ef.model.LogDto;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @author jdiaz86
 */
public interface LogDao {
    
    Log findOne(Long id);
    
    List<Log> findAll();

    void save(Log log);
    
    void saveBatch(List<Log> logs);

    Long update(Log log);
    
    void removeAll();
    
    void printVersion();
    
    List<LogDto> process(LocalDateTime from, LocalDateTime to, Long threshold);
    
}
