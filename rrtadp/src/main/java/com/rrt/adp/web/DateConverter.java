package com.rrt.adp.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DateConverter.class);
	@Override
    public Date convert(String stringDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            LOGGER.error("date convert error. [{}]", e.getMessage());
        }
        return null;
    }
}
