package by.kachanov.shop.service.converter;

import by.kachanov.shop.dto.condition.Expression;
import by.kachanov.shop.dto.condition.Or;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrConverter implements Converter<Or, Criterion> {

    @Autowired
    @Qualifier("conditionConverter")
    private ConversionService expressionConverter;

    @Override
    public Criterion convert(Or source) {
        List<Criterion> subCriteria = source.getExpressions().stream()
                .map(Expression::getActiveCondition)
                .map(condition -> expressionConverter.convert(condition, Criterion.class))
                .collect(Collectors.toList());
        return Restrictions.disjunction(subCriteria.toArray(new Criterion[]{}));
    }
}