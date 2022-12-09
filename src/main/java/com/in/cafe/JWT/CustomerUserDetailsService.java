package com.in.cafe.JWT;

import com.in.cafe.POJO.User;
import com.in.cafe.dao.UserDao;
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
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    private User userDetails;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        userDetails = userDao.findByEmailId(username);
        log.info("Inside loadByUsername {}",username);
        if(!Objects.isNull(userDetails)){
            return new org.springframework.security.core.userdetails.User(userDetails.getEmail(),userDetails.getPassword(),new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("user not found");
        }

    }

    public User getUserDetails(){

        return userDetails;
    }
}
