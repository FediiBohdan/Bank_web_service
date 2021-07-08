package ua.fedii.bankapp;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.fedii.bankapp.model.TransferBalance;

import java.math.BigDecimal;

@RestController("/balance")
@AllArgsConstructor //@Autowired -> bankService
public class BalanceController {

    private final BankService bankService;

    @GetMapping("/{accountId}")
    public BigDecimal getBalance(@PathVariable Long accountId) {
        return bankService.getBalance(accountId);
    }

    @PostMapping("/add")
    public BigDecimal addMoney(
            @RequestBody TransferBalance transferBalance) {
        return bankService.addMoney(transferBalance.getTo(), transferBalance.getAmount());
    }

    @PostMapping("/transfer")
    public void transfer(
            @RequestBody TransferBalance transferBalance) {
        bankService.makeTransfer(transferBalance);
    }
}
