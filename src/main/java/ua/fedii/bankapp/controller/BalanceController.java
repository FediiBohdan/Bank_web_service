package ua.fedii.bankapp.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ua.fedii.bankapp.service.BankService;
import ua.fedii.bankapp.model.TransferBalance;

import java.math.BigDecimal;

@Slf4j
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

    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e) {
        log.error(e.getMessage());
        return "Broken...";
    }
}
