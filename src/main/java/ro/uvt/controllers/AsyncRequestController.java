
package ro.uvt.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.uvt.execution.AsyncRequestService;
import ro.uvt.execution.AsyncRequestStatus;

import java.util.Map;

@RestController
@RequestMapping("/requests")
public class AsyncRequestController {

    private final AsyncRequestService asyncRequestService;

    public AsyncRequestController(AsyncRequestService asyncRequestService) {
        this.asyncRequestService = asyncRequestService;
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<?> getRequestStatus(
            @PathVariable String requestId
    ) {
        AsyncRequestStatus status =
                asyncRequestService.getStatus(requestId);

        if (status == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Map.of(
                "requestId", status.getRequestId(),
                "state", status.getState().name(),
                "result", status.getResult() == null ? "" : status.getResult(),
                "error", status.getError() == null ? "" : status.getError()
        ));
    }
}
