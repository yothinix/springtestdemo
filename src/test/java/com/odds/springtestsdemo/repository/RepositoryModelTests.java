package com.odds.springtestsdemo.repository;

import org.hibernate.validator.HibernateValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;

public class RepositoryModelTests {
    private LocalValidatorFactoryBean localValidatorFactory;

    @BeforeEach
    void setUp() {
        localValidatorFactory = new LocalValidatorFactoryBean();
        localValidatorFactory.setProviderClass(HibernateValidator.class);
        localValidatorFactory.afterPropertiesSet();
    }

    @Test
    void testIdFieldShouldNotBeNull() {
        RepositoryModel model = new RepositoryModel();
        model.setName("Banana");

        ConstraintViolation<RepositoryModel> actual = localValidatorFactory
                .validate(model)
                .iterator()
                .next();

        Assertions.assertEquals("id", actual.getPropertyPath().toString());
        Assertions.assertEquals("must not be null", actual.getMessage());
    }
}
