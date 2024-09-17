package org.e2cho.e2cho_HW.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.dto.user.Registration;
import org.e2cho.e2cho_HW.dto.user.RegistrationInfoDelete;
import org.e2cho.e2cho_HW.service.user.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final RegistrationService registrationService;

    @PostMapping("/registration")
    public ResponseEntity<Registration.Response> userRegistration(
            @Valid @RequestBody Registration.Request request
    ) {

        Registration.Dto dto = registrationService.userRegistration(request);

        return new ResponseEntity<>(Registration.Response.createNewResponse(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RegistrationInfoDelete.Response> deleteRegistrationInfo(@PathVariable("id") Long userId){

        RegistrationInfoDelete.Dto dto = registrationService.deleteRegistrationInfo(userId);

        return new ResponseEntity<>(RegistrationInfoDelete.Response.createNewResponse(dto), HttpStatus.OK);
    }
}
