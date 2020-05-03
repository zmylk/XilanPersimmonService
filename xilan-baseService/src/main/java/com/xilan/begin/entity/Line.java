package com.xilan.begin.entity;

import lombok.Data;

import java.util.List;

/**
 * @author like
 * @version 1.0
 * @date 2020/4/22 18:22
 */
@Data
public class Line {
    private String[] xAria;
    private List<Double>  stData;
    private List<Double> etData;
    private List<Double> ptData;


}
