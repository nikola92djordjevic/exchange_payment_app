package rs.payment.exchange.controller;

import PlatniPrometDB.tables.pojos.WorkPlace;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.payment.exchange.dto.respones.RpcResponse;
import rs.payment.exchange.service.WorkPlaceService;

import java.util.List;

@RestController
@RequestMapping("/api/workPlace")
public class WorkPlaceController {

    private final WorkPlaceService workPlaceService;

    public WorkPlaceController(WorkPlaceService workPlaceService){
        this.workPlaceService = workPlaceService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/open",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> openWorkPlace(@RequestBody WorkPlace workPlace) {

        RpcResponse response = workPlaceService.openWorkPlace(workPlace);

        return ResponseEntity.status(response.getResponseCode()).body(response);
    }


    @RequestMapping(
            method = RequestMethod.POST,
            path = "/close",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<RpcResponse> closeWorkPlace(@RequestBody WorkPlace workPlace) {

        RpcResponse response = workPlaceService.closeWorkPlace(workPlace.getWorkPlaceId());

        return ResponseEntity.status(response.getResponseCode()).body(response);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getActiveWorkPlace",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WorkPlace> getActiveWorkPlace() {

        WorkPlace response = workPlaceService.getActiveWorkPlace();

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getWorkPlace/{workPlaceId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<WorkPlace> getWorkPlace(@PathVariable Long workPlaceId) {

        WorkPlace response = workPlaceService.getWorkPlace(workPlaceId);

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/getAllWorkPlaces",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<WorkPlace>> getAllWorkPlaces() {

        List<WorkPlace> response = workPlaceService.getAllWorkPlaces();

        if (response == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(response);
        }
    }
}
