package kz.abylai.solva.api;

import kz.abylai.solva.services.TransactionService;
import kz.abylai.solva.dtos.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;


    @PostMapping("/save")
    public ResponseEntity<?> saveTransaction(@RequestBody TransactionDto transactionDto) {
        try {
            transactionService.saveTransaction(transactionDto);
            return ResponseEntity.ok("Transaction saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save transaction: " + e.getMessage());
        }
    }
}