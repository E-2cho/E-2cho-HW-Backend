package org.e2cho.e2cho_HW.controller.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.dto.user.DeviceRegistration;
import org.e2cho.e2cho_HW.dto.user.NicknameRegistration;
import org.e2cho.e2cho_HW.dto.user.RegistrationInfoDelete;
import org.e2cho.e2cho_HW.dto.user.UserInfo;
import org.e2cho.e2cho_HW.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/registration/nickname")
    public ResponseEntity<NicknameRegistration.Response> registerNickname(
            @Valid @RequestBody NicknameRegistration.Request request
    ) {

        NicknameRegistration.Dto dto = userService.registerNickname(request);

        return new ResponseEntity<>(NicknameRegistration.Response.createNewResponse(dto), HttpStatus.CREATED);
    }

    @PostMapping("/registration/{id}/userDevice")
    public ResponseEntity<DeviceRegistration.Response> registerUserDevice(
            @PathVariable("id") Long userId,
            @Valid @RequestBody DeviceRegistration.Request request
    ) {

        DeviceRegistration.Dto dto = userService.registerDevice(userId, request);

        return new ResponseEntity<>(DeviceRegistration.Response.createNewResponse(dto), HttpStatus.CREATED);
    }

    @GetMapping("/userInfo")
    public ResponseEntity<UserInfo.Response> getUserInfo(
            @RequestParam("deviceName") String deviceName
    ) {

        UserInfo.Dto dto = userService.getUserInfo(deviceName);

        return new ResponseEntity<>(UserInfo.Response.createNewResponse(dto), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<RegistrationInfoDelete.Response> deleteRegistrationInfo(@PathVariable("id") Long userId){

        RegistrationInfoDelete.Dto dto = userService.deleteRegistrationInfo(userId);

        return new ResponseEntity<>(RegistrationInfoDelete.Response.createNewResponse(dto), HttpStatus.OK);
    }
}
