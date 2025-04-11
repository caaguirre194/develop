package com.caaguirre.develop.service;

import java.util.List;
import java.util.Optional;

public interface OperationService {

    Optional<Integer> getMax(List<Integer> list);

    Optional<Integer> getMin(List<Integer> list);

    Integer getSum(List<Integer> list);

    Optional<Integer> getDivision(Integer a, Integer b);

}
