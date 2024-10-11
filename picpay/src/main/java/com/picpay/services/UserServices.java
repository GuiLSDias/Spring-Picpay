package com.picpay.services;

import com.picpay.domain.user.User;
import com.picpay.domain.user.UserType;
import com.picpay.dtos.UserDTO;
import com.picpay.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;


@Service
public class UserServices {

    @Autowired
    private UserRepositories repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuario do tipo Lojista nao esta autorizado a realizar transacao");
        }
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("Usuario nao encontrado"));
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
         this.saveUser(newUser);
         return newUser;
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }
}
