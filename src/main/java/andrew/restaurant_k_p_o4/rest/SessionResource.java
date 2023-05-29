package andrew.restaurant_k_p_o4.rest;

import andrew.restaurant_k_p_o4.model.SessionDTO;
import andrew.restaurant_k_p_o4.service.SessionService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
public class SessionResource {

    private final SessionService sessionService;

    public SessionResource(final SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public ResponseEntity<List<SessionDTO>> getAllSessions() {
        return ResponseEntity.ok(sessionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDTO> getSession(@PathVariable(name = "id") final Integer id) {
        return ResponseEntity.ok(sessionService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createSession(@RequestBody @Valid final SessionDTO sessionDTO) {
        final Integer createdId = sessionService.create(sessionDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSession(@PathVariable(name = "id") final Integer id,
            @RequestBody @Valid final SessionDTO sessionDTO) {
        sessionService.update(id, sessionDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSession(@PathVariable(name = "id") final Integer id) {
        sessionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
