package org.e2cho.e2cho_HW.service.util;

import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.constant.ErrorType;
import org.e2cho.e2cho_HW.domain.user.User;
import org.e2cho.e2cho_HW.exception.CustomErrorException;
import org.e2cho.e2cho_HW.repository.user.UserRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserUtilService {

    private final UserRepository userRepository;

    public void validateUser(Long userId) {
        if (userRepository.findById(userId).isEmpty()){
            throw new CustomErrorException(ErrorType.UserNotFoundError);
        }
    }

    public User getValidateUser(Long userId) {

        return userRepository.findById(userId)
                .orElseThrow(()-> new CustomErrorException(ErrorType.UserNotFoundError));

    }
}
