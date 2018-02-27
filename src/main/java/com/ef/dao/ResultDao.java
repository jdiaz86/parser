package com.ef.dao;

import com.ef.model.Result;
import java.util.List;

/**
 *
 * @author jdiaz86
 */
public interface ResultDao {
    
    Result findOne(Long id);
    
    List<Result> findAll();

    void save(Result log);

    Long update(Result log);
    
}
