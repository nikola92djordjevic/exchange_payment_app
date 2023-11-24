package rs.payment.exchange.controller;

import PlatniPrometDB.tables.pojos.CashRegister;
import PlatniPrometDB.tables.pojos.ExchangeJob;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.payment.exchange.dto.requests.ExchangeMoneyRequest;
import rs.payment.exchange.dto.respones.CommissionUserResponse;
import rs.payment.exchange.dto.respones.ExchangeRateResponse;
import rs.payment.exchange.dto.respones.RpcResponse;
import rs.payment.exchange.service.ExchangeJobService;

import java.util.List;

@RestController
@RequestMapping("/api/exchangeJob")
public class ExchangeJobController {

    private final ExchangeJobService exchangeJobService;

    public ExchangeJobController(ExchangeJobService exchangeJobService) {
        this.exchangeJobService = exchangeJobService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/exchangeMoney",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> exchangeMoney(@RequestBody ExchangeMoneyRequest exchangeMoneyRequest) {

        RpcResponse response = exchangeJobService.exchangeMoney(exchangeMoneyRequest);

        if (response == null) {
            return ResponseEntity.internalServerError().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getExchangeJobRecords",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ExchangeJob>> getAllExchangeRates() {

        List<ExchangeJob> response = exchangeJobService.getExchangeJobRecords();

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/currentCommission",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommissionUserResponse> getCurrentCommission() {

        CommissionUserResponse response = exchangeJobService.getCurrentCommission();

        if (response == null) {
            return ResponseEntity.internalServerError().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/weekCommission",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CommissionUserResponse>> getWeekCommission() {

        List<CommissionUserResponse> response = exchangeJobService.getWeekCommission();

        if (response == null) {
            return ResponseEntity.internalServerError().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/calculateCommissionPerDay/{dayNumber}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CommissionUserResponse>> calculateCommissionPerDay(@PathVariable Long dayNumber) {

        List<CommissionUserResponse> response = exchangeJobService.calculateCommissionPerDay(dayNumber);

        if (response == null) {
            return ResponseEntity.internalServerError().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/usersCommission",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> userCommission(@RequestBody ExchangeMoneyRequest exchangeMoneyRequest) {

        RpcResponse response = exchangeJobService.exchangeMoney(exchangeMoneyRequest);

        if (response == null) {
            return ResponseEntity.internalServerError().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }


}
