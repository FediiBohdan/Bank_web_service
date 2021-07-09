package ua.fedii.bankapp.service;

import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import ua.fedii.bankapp.model.TransferBalance;
import ua.fedii.bankapp.repository.BalanceRepository;

import java.math.BigDecimal;

@Service
@AllArgsConstructor//@Autowired -> balanceRepository
public class BankService {

    private final BalanceRepository repository;

    public BigDecimal getBalance(Long accountId) {
        BigDecimal balance = repository.getBalanceForId(accountId);

        if (balance == null) {
            throw new IllegalArgumentException();
        }

        return balance;
    }

    public BigDecimal addMoney(Long to, BigDecimal amount) {
        BigDecimal currentBalance = repository.getBalanceForId(to);
        if (currentBalance == null) {
            repository.save(to, amount);
            return amount;
        } else {
            BigDecimal updatedBalance = currentBalance.add(amount);
            repository.save(to, updatedBalance);
            return updatedBalance;
        }
    }

    public void makeTransfer(TransferBalance transferBalance) {
        BigDecimal fromBalance = repository.getBalanceForId(transferBalance.getFrom());
        BigDecimal toBalance = repository.getBalanceForId(transferBalance.getTo());

        if (fromBalance == null || toBalance == null) throw new IllegalArgumentException("No ids");
        if (transferBalance.getAmount().compareTo(fromBalance) > 0) throw new IllegalArgumentException("Not enough money!");

        BigDecimal updatedFromBalance = fromBalance.subtract(transferBalance.getAmount());
        BigDecimal updatedToBalance = toBalance.add(transferBalance.getAmount());

        repository.save(transferBalance.getFrom(), updatedFromBalance);
        repository.save(transferBalance.getTo(), updatedToBalance);
    }
}
