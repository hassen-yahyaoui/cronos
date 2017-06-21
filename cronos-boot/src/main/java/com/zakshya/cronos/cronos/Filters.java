package com.zakshya.cronos.cronos;


import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class Filters {

    private static Map<String, Predicate> predicates;

    static {
        predicates = new HashMap<>();
        predicates.put(Method.class.getName(), methodsPredicate());
        predicates.put(Class.class.getName(), beansPredicate());
    }

    static <T> Set<T> filter(Set<T> in) {

        if (!CollectionUtils.isEmpty(in)) {
            String type = in.iterator().next().getClass().getName();
            if (!predicates.containsKey(type)) {
                throw new IllegalArgumentException("Can not filter type: " + type);
            }

            return (Set<T>) in.stream().
                    filter(predicates.get(type)).
                    collect(Collectors.toSet());
        }
        return new HashSet<T>();
    }

    private static Predicate<Method> methodsPredicate() {
        return method -> {
            return Modifier.isPublic(method.getModifiers());
        };
    }

    private static Predicate<Class> beansPredicate() {
        return bean -> {
            return !bean.getName().startsWith("com.zakshya.cronos");
        };
    }
}
