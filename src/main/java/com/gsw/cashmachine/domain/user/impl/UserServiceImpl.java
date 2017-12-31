package com.gsw.cashmachine.domain.user.impl;

import com.gsw.cashmachine.domain.transaction.Transaction;
import com.gsw.cashmachine.domain.transaction.TransactionRepository;
import com.gsw.cashmachine.domain.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * A classe UserService e reponsavel por gerenciar as operacoes de CRUD de usuario.
 *
 * @author Eduardo Alves
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * O metodo e reponsavel por salvar um objeto usuario.
     * Caso o usuario ja exista no banco de dados e retornado uma excecao
     *
     * @param user
     * @return User
     * @throws ExistUserException
     */
    @Override
    @Transactional
    public User save(final User user) throws ExistUserException {
        User userEntity = this.userRepository.findByUsername(user.getUsername());
        if (userEntity != null) throw new ExistUserException();
        else {
            userEntity = new User(user.getUsername(), encoder.encode(user.getPassword()), user.getEmail(), user.getAuthorities(), user.getAccount());
            userRepository.save(userEntity);
        }
        return userEntity;
    }

    /**
     * O metodo e reponsavel por editar um objeto usuario.
     * Caso o usuario informe um novo password será gerado uma nova criptografica
     * para persistir no banco de dados.
     *
     * @param user
     */
    @Override
    public User edit(final User user) throws ExistUserException {
        User userEntity = this.userRepository.findByUsername(user.getUsername());
        if (userEntity != null) throw new ExistUserException();
        if (user.getPassword().equals("") || user.getPassword() == null) {
            userEntity.setPassword(userEntity.getPassword());
            return userRepository.save(user);
        } else {
            return userRepository.save(new User(user.getId(), user.getUsername(), encoder.encode(user.getPassword()), user.getAuthorities(), user.getEmail(), user.getAccount()));
        }
    }

    /**
     * O metodo e reponsavel por deletar usuario.
     *
     * @param user
     */
    @Override
    public void delete(final User user) throws UserNotFoundException {
        User byUsername = loadUserByUsername(user.getUsername());
        userRepository.delete(byUsername);
    }

    /**
     * O metodo e reponsavel por retornar um usuario realizando a busca por nome
     *
     * @param username
     * @return User
     */
    @Override
    public User loadUserByUsername(String username) throws UserNotFoundException {
        User userEntity = this.userRepository.findByUsername(username);
        if (userEntity == null) throw new UserNotFoundException();
        return userEntity;
    }

    @Override
    public User loadUserById(Long id) throws UserNotFoundException {
        User userEntity = userRepository.findById(id);
        if (userEntity == null) throw new UserNotFoundException();
        return userEntity;
    }

    /**
     * O metodo e reponsavel por retornar uma lista de usuarios.
     *
     * @return
     */
    @Override
    public List<User> getAllUsers() throws UserNotFoundException {
        List<User> userList = userRepository.findAll();
        if (userList == null) {
            throw new UserNotFoundException("Users not found");
        }
        return userList;
    }

}
