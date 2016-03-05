package by.kachanov.shop.dao;

import by.kachanov.shop.dto.condition.Expression;
import by.kachanov.shop.service.ExpressionConversionService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private ExpressionConversionService expressionConversionService;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected Criteria getCriteria(Class<?> type, Expression expression) {
        Criteria criteria = getCurrentSession().createCriteria(type);
        Criterion criterion = expressionConversionService.convertExpression(expression);
        return criteria.add(criterion);
    }

}
