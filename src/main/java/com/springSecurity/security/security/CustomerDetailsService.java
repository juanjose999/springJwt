package com.springSecurity.security.security;

import com.springSecurity.security.dao.UserDao;
import com.springSecurity.security.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Slf4j
@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    private User userDetail;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("dentro de loadUserByUserName {}", username);
        userDetail = userDao.findByEmail(username);

        if(!Objects.isNull(userDetail)){
            return new org.springframework.security.core.userdetails.User(userDetail.getEmail(), userDetail.getPassword(), new ArrayList<>());

        }else {
            throw new UsernameNotFoundException("usuario no encontrado.");
        }
    }

    public User getUserDetail(){
        return userDetail;
    }
}
