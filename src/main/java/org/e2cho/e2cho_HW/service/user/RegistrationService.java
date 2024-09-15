package org.e2cho.e2cho_HW.service.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.e2cho.e2cho_HW.domain.user.User;
import org.e2cho.e2cho_HW.dto.user.Registration;
import org.e2cho.e2cho_HW.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    @Transactional
    public Registration.Dto userRegistration(Registration.Request request){

        User newUser = userRepository.save(User.registrationRequestToUser(request));

        return Registration.Dto.fromEntity(newUser);
    }
}
