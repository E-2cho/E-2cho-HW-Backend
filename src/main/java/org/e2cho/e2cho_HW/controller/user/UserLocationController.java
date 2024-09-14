package org.e2cho.e2cho_HW.controller.user;


import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.dto.user.Location;
import org.e2cho.e2cho_HW.service.user.UserLocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/location")
public class UserLocationController {

    private final UserLocationService userLocationService;

    @PostMapping("/{id}/save")
    public ResponseEntity<Location.Response> saveUserLocation(
            @RequestBody Location.Request request,
            @PathVariable("id") Long userId
    ) {

        Location.Dto dto = userLocationService.saveUserLocation(userId, request);

        return new ResponseEntity<>(Location.Response.createNewResponse(dto), HttpStatus.CREATED);
    }
}
