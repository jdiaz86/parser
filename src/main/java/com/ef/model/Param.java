package com.ef.model;

import java.util.Date;
import lombok.Data;

/**
 * class to handle entry parameters
 * @author jdiaz86
 */
@Data
public class Param {
    
    private String accessLog;
    private Date startDate;
    private String duration;
    private Long threshold;
    
}
