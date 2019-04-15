package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

@SpringBootApplication
@Slf4j
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private Repo repo;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        MyEntity e = new MyEntity();
        ZonedDateTime now = ZonedDateTime.now();
        e.setJavaUtilDate(new Date());
        now.withZoneSameInstant(TimeZone.getTimeZone("PST").toZoneId());
        e.setZonedDateTime(now);

        long nowMs = new Date().getTime();
        java.sql.Date sqlDate = new java.sql.Date(nowMs);
        e.setSqlDate(sqlDate);

        e.setTimestamp(new Timestamp(nowMs));

        Calendar calendar = Calendar.getInstance(
                TimeZone.getTimeZone("PST"));
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 10);
        calendar.set(Calendar.DAY_OF_MONTH, 15);
        calendar.set(Calendar.HOUR, 22);
        e.setCalendar(calendar);
        repo.save(e);

        MyEntity load = repo.findById(1L).get();
        log.info("{}, {}", load.getCalendar().getTimeZone(), load.getZonedDateTime().getZone());
    }
}
