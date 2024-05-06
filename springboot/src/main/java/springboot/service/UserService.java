package springboot.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springboot.model.User;
import springboot.repositories.UsersRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserService {


    private final UsersRepository usersRepository;

    @Autowired
    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<User> findAll() {
        return usersRepository.findAll();
    }

    public User findById(long id) {
        //Optional используется для управления возможным отсутствием результата поиска
        // (возвращаемого значением findById).
        Optional<User> foundUser = usersRepository.findById(id);

        //.orElse(null) возвращает значение внутри Optional, если оно присутствует,
        // иначе возвращает указанное значение по умолчанию, в данном случае - null).
        return foundUser.orElse(null); //
    }

    @Transactional
    public void save(User user) {
        usersRepository.save(user);
    }

    @Transactional
    public void update(Long id, User updateUser) {
        updateUser.setId(id);
        usersRepository.save(updateUser);
    }

    @Transactional
    public void delete(long id) {
        usersRepository.deleteById(id);
    }

}