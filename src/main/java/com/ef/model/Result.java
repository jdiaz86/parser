package com.ef.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 * entity class for RESULT table
 * @author jdiaz86
 */
@Entity
@Table(name = "RESULT")
@Data
public class Result implements Serializable {
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IP")
    private String ip;

    @Column(name = "TIMES")
    private Long times;
    
    @Column(name = "COMMENTS")
    private String comments;
    
    @Column(name = "SAVED_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date savedDate;
    
}
