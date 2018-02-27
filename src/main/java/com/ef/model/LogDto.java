package com.ef.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author jdiaz86
 */
@Data
public class LogDto implements Serializable {
    
    private String ip;
    private Long times;
    private String comments;
    private Date savedDate;
    
}
