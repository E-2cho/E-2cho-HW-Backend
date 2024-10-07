package org.e2cho.e2cho_HW.service.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.domain.user.User;
import org.e2cho.e2cho_HW.domain.user.UserDevice;
import org.e2cho.e2cho_HW.dto.user.DeviceRegistration;
import org.e2cho.e2cho_HW.dto.user.NicknameRegistration;
import org.e2cho.e2cho_HW.dto.user.RegistrationInfoDelete;
import org.e2cho.e2cho_HW.dto.user.UserInfo;
import org.e2cho.e2cho_HW.repository.user.UserDeviceRepository;
import org.e2cho.e2cho_HW.repository.user.UserRepository;
import org.e2cho.e2cho_HW.service.util.UserUtilService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDeviceRepository userDeviceRepository;

    private final UserUtilService userUtilService;

    @Transactional
    public NicknameRegistration.Dto registerNickname(NicknameRegistration.Request request){

        User newUser = userRepository.save(User.registrationRequestToUser(request));

        return NicknameRegistration.Dto.fromEntity(newUser);
    }


    @Transactional
    public DeviceRegistration.Dto registerDevice(Long userId, DeviceRegistration.Request request){

        // 1. userId로 찾은 유저가 존재하는지 검증
        User validateUser = userUtilService.getValidatedUserByUserId(userId);

        // 2. 해당 유저에 device 저장.
        UserDevice newUserDevice = userDeviceRepository.save(UserDevice.of(validateUser, request));

        return DeviceRegistration.Dto.fromEntity(newUserDevice);

    }

    public UserInfo.Dto getUserInfo(String deviceName){

        User foundUserByDeviceName = userUtilService.getValidatedUserByDeviceName(deviceName);

        return UserInfo.Dto.fromEntity(foundUserByDeviceName);

    }


    @Transactional
    public RegistrationInfoDelete.Dto deleteRegistrationInfo(Long userId){

        User foundUser = userUtilService.getValidatedUserByUserId(userId);

        Long deletedUserId = foundUser.getId();
        String deletedUserNickname = foundUser.getNickname();

        userRepository.delete(foundUser);

        return RegistrationInfoDelete.Dto.of(deletedUserId, deletedUserNickname);
    }

}
