package by.kachanov.shop.service.rsql.converters.jpa;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class DateConverter implements JpaParameterConverter<Date> {

    private static final String DATE_PATTERN = "dd-MMM-yyyy";

    @Override
    public Date convert(String source) {
        try {
            return new SimpleDateFormat(DATE_PATTERN, Locale.US).parse(source);
        } catch (ParseException e) {
            throw new IllegalArgumentException("String " + source + " is not parseable by pattern " + DATE_PATTERN);
        }
    }
}
