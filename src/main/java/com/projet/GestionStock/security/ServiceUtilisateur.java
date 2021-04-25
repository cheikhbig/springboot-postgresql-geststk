package com.projet.GestionStock.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projet.GestionStock.Repository.UtilisateurRepository;
@Service
public class ServiceUtilisateur  implements UserDetailsService {
	 @Autowired
	    private UtilisateurRepository userRepo;
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepo.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with username %s not found", username)));
    }

}
