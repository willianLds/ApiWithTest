package io.github.willianlds.apiWithTest.services.Impl;

import io.github.willianlds.apiWithTest.domain.User;
import io.github.willianlds.apiWithTest.domain.dto.UserDTO;
import io.github.willianlds.apiWithTest.repositories.UserRepository;
import io.github.willianlds.apiWithTest.services.UserService;
import io.github.willianlds.apiWithTest.services.exceptions.DataIntegratyViolationException;
import io.github.willianlds.apiWithTest.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    @Override
    public List<User> findAll(){
        return repository.findAll();
    }

    @Override
    public User create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public User update(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, User.class));
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id)
                .map(x -> {
                    repository.delete(x);
                    return x;
                }).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
    }

    private void findByEmail(UserDTO obj) {
        Optional<User> user = repository.findByEmail(obj.getEmail());
        if(user.isPresent()){
            throw new DataIntegratyViolationException("E-mail já cadastrado no sistema");
        }
    }


}
