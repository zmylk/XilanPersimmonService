package com.xilan.common.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author like
 * @version 1.0
 * @date 2020/3/25 11:21
 */
@Data
public class SimpleResult {
    Map<String,String> data;
    Integer status;
}
