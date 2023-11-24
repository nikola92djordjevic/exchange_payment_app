package rs.payment.exchange.controller;

import PlatniPrometDB.tables.pojos.CashRegister;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.payment.exchange.dto.respones.CashRegisterResponse;
import rs.payment.exchange.dto.respones.RpcResponse;
import rs.payment.exchange.service.CashRegisterService;

import java.util.List;

@RestController
@RequestMapping("/api/cashRegister")
public class CashRegisterController {

    private final CashRegisterService cashRegisterService;

    public CashRegisterController(CashRegisterService cashRegisterService){
        this.cashRegisterService = cashRegisterService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> createCashRegister(@RequestBody CashRegister cashRegister) {

        RpcResponse response = cashRegisterService.createCashRegister(cashRegister);

        return ResponseEntity.status(response.getResponseCode()).body(response);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/edit",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> editCashRegister(@RequestBody CashRegister cashRegister) {

        RpcResponse response = cashRegisterService.editCashRegister(cashRegister);

        return ResponseEntity.status(response.getResponseCode()).body(response);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getCashRegister/{cashRegisterId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CashRegisterResponse> getCashRegister(@PathVariable Long cashRegisterId) {

        CashRegisterResponse response = cashRegisterService.getCashRegister(cashRegisterId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getAllCashRegisters",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CashRegisterResponse>> getAllCashRegisters() {

        List<CashRegisterResponse> response = cashRegisterService.getAllCashRegisters();

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getAllTreasuries",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CashRegister>> getAllTreasuries() {

        List<CashRegister> response = cashRegisterService.getAllTreasuries();

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getAllFreeCashRegisters",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CashRegister>> getAllFreeCashRegisters() {

        List<CashRegister> response = cashRegisterService.getAllFreeCashRegisters();

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }
    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/deleteCashRegister/{cashRegisterId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> deleteCashRegister(@PathVariable Long cashRegisterId) {

        RpcResponse response = cashRegisterService.deleteCashRegister(cashRegisterId);

        return ResponseEntity.status(response.getResponseCode()).body(response);
    }
}
