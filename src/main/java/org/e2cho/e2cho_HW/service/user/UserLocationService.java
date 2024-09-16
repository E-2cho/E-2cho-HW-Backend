package org.e2cho.e2cho_HW.service.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.constant.ErrorType;
import org.e2cho.e2cho_HW.domain.user.User;
import org.e2cho.e2cho_HW.domain.user.UserLocation;
import org.e2cho.e2cho_HW.dto.user.Location;
import org.e2cho.e2cho_HW.exception.CustomErrorException;
import org.e2cho.e2cho_HW.repository.user.UserLocationRepository;
import org.e2cho.e2cho_HW.repository.user.UserRepository;
import org.e2cho.e2cho_HW.service.util.UserUtilService;
import org.springframework.stereotype.Service;


import static org.e2cho.e2cho_HW.constant.LocationBoundary.*;

@Service
@RequiredArgsConstructor
public class UserLocationService {

    private final UserRepository userRepository;
    private final UserLocationRepository userLocationRepository;

    private final UserUtilService userUtilService;

    @Transactional
    public Location.Dto saveUserLocation(Long userId, Location.Request request){

        // 1. 경로변수로 넘겨받은 userId를 가진 유저가 존재하는지 검증
        User foundUser = userUtilService.getValidateUser(userId);

        // 2. request 로 받은 경위도 검사
        if (!(EXTREME_SOUTH.getCoordinate() < request.getLatitude() && request.getLatitude() < EXTREME_NORTH.getCoordinate())
                || !(EXTREME_WEST.getCoordinate() < request.getLongitude() && request.getLongitude() < EXTREME_EAST.getCoordinate())){

            throw new CustomErrorException(ErrorType.OutOfBoundaryError);
        }

        // 3. 유저의 위치 정보가 이미 있다면, 경, 위도, 업데이트 시간 업데이트 없으면 처음 저장 로직 실행.
        UserLocation foundUserLocation = userLocationRepository.findByUserId(foundUser.getId());

        if (foundUserLocation != null){
               foundUserLocation.update(request);

        }else {
            foundUserLocation = userLocationRepository.save(UserLocation.fromRequestDto(foundUser, request));
        }

        return Location.Dto.of(foundUser, foundUserLocation);
    }

}
