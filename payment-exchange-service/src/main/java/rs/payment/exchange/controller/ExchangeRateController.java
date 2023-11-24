package rs.payment.exchange.controller;

import PlatniPrometDB.tables.pojos.Currency;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.payment.exchange.dto.requests.ExchangeRateCreateRequest;
import rs.payment.exchange.dto.respones.ExchangeRateResponse;
import rs.payment.exchange.dto.respones.RpcResponse;
import rs.payment.exchange.service.ExchangeRateService;

import java.util.List;

@RestController
@RequestMapping("/api/exchangeRate")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService){
        this.exchangeRateService = exchangeRateService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> CreateExchangeRate(@RequestBody ExchangeRateCreateRequest exchangeRateCreateRequest) {

        RpcResponse response = exchangeRateService.createExchangeRate(exchangeRateCreateRequest);

        return ResponseEntity.status(response.getResponseCode()).body(response);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> editExchangeRate(@RequestBody ExchangeRateCreateRequest exchangeRateCreateRequest) {

        RpcResponse response = exchangeRateService.editExchangeRate(exchangeRateCreateRequest);

        return ResponseEntity.status(response.getResponseCode()).body(response);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/delete/{exchangeRateId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> deleteExchangeRate(@PathVariable Long exchangeRateId) {

        RpcResponse response = exchangeRateService.deleteExchangeRate(exchangeRateId);

        return ResponseEntity.status(response.getResponseCode()).body(response);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getAllExchangeRates",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ExchangeRateResponse>> getAllExchangeRates() {

        List<ExchangeRateResponse> response = exchangeRateService.getAllExchangeRates();

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getExchangeRate/{exchangeRateId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ExchangeRateResponse> getExchangeRate(@PathVariable Long exchangeRateId) {

        ExchangeRateResponse response = exchangeRateService.getExchangeRate(exchangeRateId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getActiveExchangeRate",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ExchangeRateResponse> getActiveExchangeRate() {

        ExchangeRateResponse response = exchangeRateService.getActiveExchangeRate();

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/currencyList",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Currency>> getCurrencyList() {

        List<Currency> currencyList = exchangeRateService.getCurrencyList();

        if (currencyList == null || currencyList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(currencyList);
        }
    }

}
