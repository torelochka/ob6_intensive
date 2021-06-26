package ru.ob6.impl.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.ob6.api.dto.UserDto;
import ru.ob6.api.forms.SignUpForm;
import ru.ob6.api.services.UserService;
import ru.ob6.impl.models.Role;
import ru.ob6.impl.models.User;
import ru.ob6.impl.repositories.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserDto> userById(UUID id) {
        Optional<User> userOptional = userRepository.findUserById(id);
        return userOptional.map(this::userToUserDto);
    }

    @Override
    public Optional<UserDto> userByEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        return userOptional.map(this::userToUserDto);

    }

    @Override
    public void saveUser(SignUpForm signUpForm) {
        userRepository.save(
                new User(
                        signUpForm.getEmail(),
                        signUpForm.getFirstName(),
                        signUpForm.getCity(),
                        passwordEncoder.encode(signUpForm.getPassword()),
                        new Role(1L, "ROLE_USER")
                )
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isPresent()) {
            return user.get();
        }
        else throw new UsernameNotFoundException("User not found");
    }

    private UserDto userToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .city(user.getCity())
                .build();
    }
}
