package com.example.helpdev.services;

import com.example.helpdev.domain.Person;
import com.example.helpdev.repositories.PersonRepository;
import com.example.helpdev.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Person> user = personRepository.findByEmail(email);
        if(user.isPresent()){
            Person p = user.get();
            return new UserSS(p.getId(), p.getEmail(), p.getPassword(), p.getProfiles());
        }
        throw new UsernameNotFoundException(email);
    }
}
