package com.springSecurity.security.service.impl;

import com.springSecurity.security.contantes.FacturaConstantes;
import com.springSecurity.security.dao.UserDao;
import com.springSecurity.security.pojo.User;
import com.springSecurity.security.service.UserService;
import com.springSecurity.security.util.FacturaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        log.info("Rejistro interno de un usuario {}", requestMap);
        try {
            if(validateSigUpMap(requestMap)){
                User user = userDao.findByEmail(requestMap.get("email"));
                if(Objects.isNull(user)){

                    return FacturaUtils.getResponseEntity("El usuario se ha registrado con exito.", HttpStatus.CREATED);

                }else {
                    return FacturaUtils.getResponseEntity("el usuario con ese email ya existe.", HttpStatus.BAD_REQUEST);
                }
            }
            else {
                return FacturaUtils.getResponseEntity(FacturaConstantes.INVALID_DATA,HttpStatus.BAD_REQUEST);
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return FacturaUtils.getResponseEntity(FacturaConstantes.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private Boolean validateSigUpMap(Map<String, String> requestMap){
        if(requestMap.containsKey("nombre") && requestMap.containsKey("numeroDeContacto")
                && requestMap.containsKey("email") && requestMap.containsKey("password")){
            return true;
        }
        return false;
    }

    private User getUserFromMap(Map<String, String> requestMap){
        User user = new User();
        user.setNombre(requestMap.get("nombre"));
        user.setNumeroDeContacto(requestMap.get("numeroDeContacto"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus(requestMap.get("false"));
        user.setRole("user");

        return user;
    }
}
