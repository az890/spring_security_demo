package com.atguigu.service;

import com.atguigu.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserService implements UserDetailsService {
    //模拟数据库中的用户数据
    static Map<String, User> map =   new HashMap<String, User>();

    static {
        com.atguigu.pojo.User user1 =  new com.atguigu.pojo.User();
        user1.setUsername("admin");
        user1.setPassword("$2a$10$qFXfKck/TVoO1as4pMzdw.47hv9CK2klwb36dzxIlO0CVAcxGP3Zi");
        user1.setTelephone("1234");

        com.atguigu.pojo.User user2 =  new com.atguigu.pojo.User();
        user2.setUsername("zhangsan");
        user2.setPassword("$2a$10$PAFSyAlKkEisuPJUQr7MQ.G3blymgw//jai2UsGYoFIaI1P22EOZa");
        user2.setTelephone("321");

        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username = " + username);
        com.atguigu.pojo.User userInDb = map.get(username);

        if (userInDb == null) {
            return null;
        }

//        String passwordInDb = "{noop}" + userInDb.getPassword();
        ArrayList<GrantedAuthority> lists = new ArrayList<>();
        lists.add(new SimpleGrantedAuthority("add"));
        lists.add(new SimpleGrantedAuthority("delete"));
        lists.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        return new org.springframework.security.core.userdetails.User(username, userInDb.getPassword(), lists);
    }
}
