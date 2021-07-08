package ua.fedii.bankapp;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.fedii.bankapp.model.TransferBalance;

import java.math.BigDecimal;

@Service
@AllArgsConstructor//@Autowired -> balanceRepository
public class BankService {

    private final BalanceRepository repository;

    public BigDecimal getBalance(Long accountId) {
        return null;
    }

    public BigDecimal addMoney(Long to, BigDecimal amount) {
        return null;
    }

    public void makeTransfer(TransferBalance transferBalance) {
    }
}
