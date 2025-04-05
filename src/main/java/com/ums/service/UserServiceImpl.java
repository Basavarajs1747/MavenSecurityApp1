package com.ums.service;

import com.ums.entity.AppUser;
import com.ums.payload.LoginDto;
import com.ums.payload.UserDto;
import com.ums.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private  JWTService jwtService;

    private AppUserRepository userRepository;

    public UserServiceImpl(JWTService jwtService, AppUserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto addUser(UserDto userDto) {
        AppUser user = mapToEntity(userDto);
//        System.out.println(user);
        AppUser savedUser = userRepository.save(user);
//        System.out.println(savedUser);
        return  mapToDto(savedUser);

    }

    @Override
    public String  verifyLogin(LoginDto loginDto) {
        Optional<AppUser> opUser = userRepository.findByUserName(loginDto.getUsername());
        if (opUser.isPresent()) {
            AppUser user = opUser.get();
            if (BCrypt.checkpw(loginDto.getPassword(), user.getPassword())) {
                return jwtService.generateToken(user);
            }
            }
            return null;
        }

      UserDto mapToDto(AppUser user){
    UserDto dto = new UserDto();
    dto.setId(user.getId());
    dto.setName(user.getName());
    dto.setUsername(user.getUserName());
    dto.setEmailId(user.getEmailId());
    dto.setPassword(user.getPassword());

    return dto;
    }

//      AppUser mapToEntity (UserDto userDto){
//        AppUser user = new AppUser();
//        user.setName(userDto.getName());
//        user.setUserName(userDto.getUsername());
//        user.setEmailId(userDto.getEmailId());
//        user.setPassword(userDto.getPassword(), BCrypt.gensalt());
//        return user;
//         }

    AppUser mapToEntity(UserDto userDto) {
        AppUser user = new AppUser(); 
        user.setName(userDto.getName());
        user.setUserName(userDto.getUsername());
        user.setEmailId(userDto.getEmailId());
        user.setPassword(BCrypt.hashpw(userDto.getPassword(),BCrypt.gensalt(12)));
        return user;
    }


}
 