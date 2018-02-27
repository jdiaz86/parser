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
 * entity class for LOG table
 * @author jdiaz86
 */
@Entity
@Table(name = "LOG")
@Data
public class Log implements Serializable {
    
    @Id
    @Column(name = "LOG_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "IP")
    private String ip;

    @Column(name = "REQUEST")
    private String request;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "USER_AGENT")
    private String userAgent;
    
}
