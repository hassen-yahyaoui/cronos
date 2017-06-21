package com.zakshya.cronos.cronos.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class Argument {

    private String name;
    private String type;
}
