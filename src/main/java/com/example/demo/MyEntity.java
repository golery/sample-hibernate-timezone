package com.example.demo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
public class MyEntity {
    @Id
    @GeneratedValue
    private Long id;

    private ZonedDateTime zonedDateTime;

    private Date javaUtilDate;

    private java.sql.Date sqlDate;

    private Timestamp timestamp;

    private Calendar calendar;
}
