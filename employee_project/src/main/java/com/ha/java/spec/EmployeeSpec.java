package com.ha.java.spec;

import com.ha.java.entity.EmployeeEntity;
import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpec {

    private EmployeeSpec() {
    }

    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String EMAIL = "email";

    public static Specification<EmployeeEntity> findByAny(String anyField){
        return (root, criteriaQuery, criteriaBuilder)
                -> criteriaBuilder.or(
                        criteriaBuilder.or(
                                criteriaBuilder.like(
                                        criteriaBuilder.lower(criteriaBuilder.concat(root.get(FIRST_NAME), root.get(LAST_NAME))),
                                        likeQuery(anyField)),
                                criteriaBuilder.like(
                                        criteriaBuilder.lower(criteriaBuilder.concat(root.get(LAST_NAME), root.get(FIRST_NAME))),
                                        likeQuery(anyField))
                        ),

                criteriaBuilder.like(root.get(FIRST_NAME), anyField),
                criteriaBuilder.like(root.get(LAST_NAME), anyField),
                criteriaBuilder.like(root.get(PHONE_NUMBER), anyField),
                criteriaBuilder.like(root.get(EMAIL), anyField)
        );
    }

    public static String likeQuery(String search) {
        return String.format("%s%s%s", "%", search.trim().toLowerCase().replaceAll("\\s", ""), "%");
    }

}
