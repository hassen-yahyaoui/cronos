package com.zakshya.cronos.cronos;

import com.zakshya.cronos.cronos.model.Argument;
import com.zakshya.cronos.cronos.model.Schedulable;
import org.springframework.context.ApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class SchedulableBuilder {

    static Set<Schedulable> buildFromApplicationContext(ApplicationContext applicationContext) {
        Set<Schedulable> schedulables = new HashSet<>();
        Filters.filter(getProcessableBeans(applicationContext)).
                forEach(bean -> schedulables.addAll(build(bean)));
        return schedulables;
    }

    private static Set<Schedulable> build(Class bean) {
        return Filters.filter(Arrays.stream(bean.getDeclaredMethods()).
                collect(Collectors.toSet())).
                stream().
                map(method -> build(method)).
                collect(Collectors.toSet());

    }

    private static Schedulable build(Method method) {
        List<Argument> arguments = Arrays.stream(method.getParameters()).
                map(parameter -> build(parameter)).
                collect(Collectors.toList());

        return Schedulable.builder().
                name(method.getName()).
                declaringClass(method.getDeclaringClass().getName()).
                arguments(arguments).
                returnType(method.getReturnType().getName()).
                build();
    }

    private static Argument build(Parameter parameter) {
        return Argument.builder().
                name(parameter.getName()).
                type(parameter.getType().getName()).
                build();
    }


    private static Set<Class> getProcessableBeans(ApplicationContext applicationContext) {
        Set<Class> processableBeans = new HashSet<>();
        applicationContext.getBeansWithAnnotation(
                org.springframework.stereotype.Service.class).
                values().forEach(bean -> processableBeans.add(bean.getClass()));
        return processableBeans;
    }

}
