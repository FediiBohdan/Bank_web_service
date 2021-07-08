package ua.fedii.bankapp;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BalanceRepository {

    //<balance key, balance>
    private final Map<Long, BigDecimal> storage = new HashMap<>();
}
