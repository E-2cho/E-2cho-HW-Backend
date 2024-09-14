package org.e2cho.e2cho_HW.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.dto.Registration;
import org.e2cho.e2cho_HW.service.user.RegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/registration")
public class UserController {

    private final RegistrationService registrationService;

    @PostMapping()
    public ResponseEntity<Registration.Response> userRegistration(
            @Valid @RequestBody Registration.Request request
    ) {

        Registration.Dto dto = registrationService.userRegistration(request);

        return new ResponseEntity<>(Registration.Response.createNewResponse(dto), HttpStatus.CREATED);
    }
}
