package com.caaguirre.develop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import io.vavr.control.Try;

@Service
@Slf4j
public class OperationServiceImpl implements OperationService {
    @Override
    public Optional<Integer> getMax(List<Integer> list) {
        return list.stream().max(Integer::compareTo);
    }

    @Override
    public Optional<Integer> getMin(List<Integer> list) {
        return list.stream().min(Integer::compareTo);
    }

    @Override
    public Integer getSum(List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }

    @Override
    public Optional<Integer> getDivision(Integer a, Integer b) {
        if (b == 0) {
            return Optional.empty();  // Return empty to avoid division by zero
        }
        return Optional.of(a / b);

//        return Try.of(() -> {
//            if (b == 0) {
//                throw new ArithmeticException("Division by zero");
//            }
//            return a / b;
//        });
    }
}
