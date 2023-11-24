package rs.payment.exchange.controller;

import PlatniPrometDB.tables.pojos.BalanceEntry;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rs.payment.exchange.dto.requests.BalanceRequest;
import rs.payment.exchange.dto.requests.TransferRequest;
import rs.payment.exchange.dto.respones.BalanceResponse;
import rs.payment.exchange.dto.respones.RpcResponse;
import rs.payment.exchange.service.BalanceService;

import java.util.List;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService){
        this.balanceService = balanceService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/createEntry",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> createBalanceEntry(@RequestBody BalanceEntry balanceEntry) {

        RpcResponse response = balanceService.createBalanceEntry(balanceEntry);

        return ResponseEntity.status(response.getResponseCode()).body(response);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/createEntries",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> createBalanceEntries(@RequestBody List<BalanceEntry> balanceEntryList) {

        RpcResponse response = balanceService.createBalanceEntries(balanceEntryList);

        return ResponseEntity.status(response.getResponseCode()).body(response);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/calculateBalance",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BalanceResponse> calculateBalance(@RequestBody BalanceRequest balanceRequest) {

        BalanceResponse response = balanceService.calculateBalance(balanceRequest);

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/transfer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> transfer(@RequestBody TransferRequest transferRequest) {

        RpcResponse response = balanceService.transfer(transferRequest);

        return ResponseEntity.status(response.getResponseCode()).body(response);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/TEST",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void TEST() {

        RpcResponse response = new RpcResponse();

        balanceService.test();


    }



}
