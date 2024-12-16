package ru.backend.UserService.services.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.backend.UserService.model.AppUser;
import ru.backend.UserService.repository.user.UserRepository;

import java.util.List;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public void add(AppUser appUser) {
        userRepository.save(appUser);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AppUser> listUsers() {
        return userRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public AppUser getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void edit(AppUser newAppUser) {
        userRepository.save(newAppUser);
    }

    @Transactional
    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

}
