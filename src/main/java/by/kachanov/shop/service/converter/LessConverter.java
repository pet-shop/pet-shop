package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Less;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LessConverter implements Converter<Less, Criterion> {

    @Override
    public Criterion convert(Less source) {
        return Restrictions.lt(source.getField(), source.getValue());
    }
}