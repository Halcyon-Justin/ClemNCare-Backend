package halcyon.clemncare.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import halcyon.clemncare.app.dto.RegistrationRequest;
import halcyon.clemncare.app.model.Family;
import halcyon.clemncare.app.response.ResponseHandler;
import halcyon.clemncare.app.service.RegistrationService;

@RestController
@RequestMapping("/api/daycare/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Object> registerNewFamily(@RequestBody RegistrationRequest registrationRequest) {
        if (registrationRequest == null) {
            return ResponseEntity.badRequest().body("Invalid request payload");
        }

        Family createdFamily = registrationService.registerNewFamily(registrationRequest);
        return ResponseHandler.responseBuilder("Registered Family", HttpStatus.CREATED, createdFamily);
    }
}

