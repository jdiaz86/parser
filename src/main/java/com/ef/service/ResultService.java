package com.ef.service;

import com.ef.model.Result;
import java.util.List;

/**
 *
 * @author jdiaz86
 */
public interface ResultService {
    
    Result findOne(Long id);
    
    List<Result> findAll();

    void save(Result result);
    
    Long update(Result result);
    
}
