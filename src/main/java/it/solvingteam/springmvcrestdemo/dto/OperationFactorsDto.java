package it.solvingteam.springmvcrestdemo.dto;

import javax.validation.constraints.NotNull;

public class OperationFactorsDto {

    @NotNull
    private Integer a;

    @NotNull
    private Integer b;

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }
}
