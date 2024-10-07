package org.e2cho.e2cho_HW.service.util;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.constant.ErrorType;
import org.e2cho.e2cho_HW.domain.user.User;
import org.e2cho.e2cho_HW.domain.user.UserDevice;
import org.e2cho.e2cho_HW.exception.CustomErrorException;
import org.e2cho.e2cho_HW.repository.user.UserDeviceRepository;
import org.e2cho.e2cho_HW.repository.user.UserRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserUtilService {

    private final UserRepository userRepository;
    private final UserDeviceRepository userDeviceRepository;

    // 유저 검증
    public void validateUser(Long userId) {
        if (userRepository.findById(userId).isEmpty()){
            throw new CustomErrorException(ErrorType.UserNotFoundError);
        }
    }

    // 검증된 유저 가져오기
    public User getValidatedUserByUserId(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(()-> new CustomErrorException(ErrorType.UserNotFoundError));

    }

    // 기기명으로 유저 정보 불러오기
    public User getValidatedUserByDeviceName(String deviceName) {

        UserDevice validatedDevice = userDeviceRepository.findByDeviceName(deviceName)
                .orElseThrow(() -> new CustomErrorException(ErrorType.DeviceNotFoundError));

        return validatedDevice.getUser();
    }
}
