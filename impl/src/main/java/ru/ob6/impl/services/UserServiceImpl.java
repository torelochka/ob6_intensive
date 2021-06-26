package ru.ob6.impl.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.ob6.api.dto.UserDto;
import ru.ob6.api.forms.SignUpForm;
import ru.ob6.api.services.UserService;
import ru.ob6.impl.models.User;
import ru.ob6.impl.repositories.UserRepository;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public Optional<UserDto> userById(Long id) {
        Optional<User> userOptional = userRepository.findUserById(id);
        return userOptional.stream().map(user -> modelMapper.map(user, UserDto.class)).findFirst();
    }

    @Override
    public Optional<UserDto> userByEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        return userOptional.stream().map(user -> modelMapper.map(user, UserDto.class)).findFirst();

    }

    @Override
    public void saveUser(SignUpForm signUpForm) {
        userRepository.save(
                User.builder()
                .email(signUpForm.getEmail())
                .firstName(signUpForm.getFirstName())
                .city(signUpForm.getCity())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .role(User.Role.ROLE_USER)
                        .build()
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
}
