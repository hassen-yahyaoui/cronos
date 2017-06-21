package com.zakshya.cronos.cronos.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Builder
@Getter
@ToString
public class Schedulable {

    private String name;
    private String declaringClass;
    private List<Argument> arguments;
    private String returnType;

}
