package com.example.demo.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;


@Service
public class UserServiceImpl implements IUserService,UserDetailsService {
	
	@Autowired
	private UserRepository urepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	public boolean usersSaved=false;
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public void saveUsers() {
		User user1=new User(1,"Dhinesh","Dhinesh17",passwordEncoder.encode("Dhinesh17"),new HashSet<String>(Arrays.asList("ADMIN","USER")));
		User user2=new User(2,"Nithla","Nithla17",passwordEncoder.encode("Nithla17"),new HashSet<String>(Arrays.asList("USER")));
		User user3=new User(3,"Rohith","Rohith17",passwordEncoder.encode("Rohith17"),new HashSet<String>(Arrays.asList("ADMIN","USER")));
		User user4=new User(4,"Anusha","Anusha17",passwordEncoder.encode("Anusha17"),new HashSet<String>(Arrays.asList("USER")));
		User user5=new User(5,"Karthikeyan","Karthi17",passwordEncoder.encode("Karthi17"),new HashSet<String>(Arrays.asList("ADMIN","USER")));
		urepo.save(user1);
		urepo.save(user2);
		urepo.save(user3);
		urepo.save(user4);
		urepo.save(user5);
		logger.info("Users are Saved Successfully");
	}
	
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(!this.usersSaved) {
			this.saveUsers();
			this.usersSaved=true;
		}
		
		Optional<User> opt=urepo.findByUsername(username);
		if(opt.isEmpty())
		{
			logger.info("Invalid User Details Entered or The Username Could not be Found!!");
			throw new UsernameNotFoundException("User Does Not Exist!");
		}
		else
		{
			logger.info("User with the given username is Found!!");
			User user=opt.get();
			return new org.springframework.security.core.userdetails.
										User(username,user.getPassword(),
												user.getRoles().stream().map(role->new SimpleGrantedAuthority(role))
												.collect(Collectors.toSet())
												);
											
		}
	}
	

}
