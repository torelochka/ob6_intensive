package ru.ob6.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.ob6.api.dto.UserDto;
import ru.ob6.api.forms.EmailForm;
import ru.ob6.api.forms.ForgotPasswordForm;
import ru.ob6.api.forms.SignUpForm;
import ru.ob6.api.forms.UserDataForm;
import ru.ob6.api.services.MailService;
import ru.ob6.api.services.UserService;
import ru.ob6.impl.models.User;
import ru.ob6.impl.repositories.UserRepository;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final MailService mailService;
    private final ModelMapper modelMapper;
    private final ExecutorService executorService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, MailService mailService, ModelMapper modelMapper, ExecutorService executorService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.modelMapper = modelMapper;
        this.executorService = executorService;
    }

    @Override
    public Optional<UserDto> userById(UUID id) {
        Optional<User> userOptional = userRepository.findUserById(id);
        return userOptional.map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public Optional<UserDto> userByEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        return userOptional.map(user -> modelMapper.map(user, UserDto.class));

    }

    @Override
    public Optional<UserDataForm> userDataByEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        return userOptional.map(user -> modelMapper.map(user, UserDataForm.class));
    }

    @Override
    public void saveUser(SignUpForm signUpForm) {
        User user = userRepository.save(
                User.builder()
                        .email(signUpForm.getEmail())
                        .firstName(signUpForm.getFirstName())
                        .city(signUpForm.getCity())
                        .password(passwordEncoder.encode(signUpForm.getPassword()))
                        .role(User.Role.ROLE_USER)
                        .build()
        );

        executorService.submit(() ->
                mailService.sendEmailForConfirm(signUpForm.getEmail(), user.getId().toString())
        );
    }

    @Override
    public boolean confirmEmail(UUID id) {
        Optional<User> userOptional = userRepository.findUserById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setEmailConfirmed(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public void saveNewUserData(UserDataForm userDataForm) {
        Optional<User> userOptional = userRepository.findUserByEmail(userDataForm.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!user.getFirstName().equals(userDataForm.getFirstName())) {
                user.setFirstName(userDataForm.getFirstName());
            }
            if (!user.getCity().equals(userDataForm.getCity())) {
                user.setCity(userDataForm.getCity());
            }
            if (!userDataForm.getPassword().equals("")) {
                user.setPassword(passwordEncoder.encode(userDataForm.getPassword()));
            }
            userRepository.save(user);
        }
    }

    @Override
    public void resetPassword(EmailForm emailForm) {
        Optional<User> userOptional = userRepository.findUserByEmail(emailForm.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            executorService.submit(() ->
                    mailService.sendEmailForResetPassword(user.getEmail(), user.getId().toString())
            );
        }
    }

    @Override
    public boolean changePassword(ForgotPasswordForm forgotPasswordForm) {
        Optional<User> userOptional = userRepository.findUserByEmail(forgotPasswordForm.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(forgotPasswordForm.getPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
