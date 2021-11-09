package com.aspire.loan.datagenerator.builder;

import com.github.javafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class BuilderSetup {

    public static final Faker faker = new Faker(Locale.UK);

    public Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public static int randomNumber(int start, int end){
        return faker.number().numberBetween(start, end);
    }

    public static TimeZone defaultTimeZone = TimeZone.getTimeZone("Asia/Singapore");

    public static <T> T getRandomValueInDefinedRange(List<T> range){
        return range.get(randomNumber(0, range.size()));
    }



}
