package azael.josue.recordatorio.service;

import org.springframework.stereotype.Service;

import azael.josue.recordatorio.model.user;
import azael.josue.recordatorio.repository.userRepository;

@Service
public class userServiceImpl extends AbstractCrudService<user, Long> implements userService {

    userServiceImpl(userRepository repository) {
        super(repository);
    }
}
