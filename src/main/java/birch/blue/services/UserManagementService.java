package birch.blue.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import birch.blue.model.Authority;
import birch.blue.model.User;
import birch.blue.repository.AuthorityRepository;
import birch.blue.repository.UserDetailsRepository;

@Service
public class UserManagementService implements IUserManagementService {

	@Autowired
	AuthorityRepository authorityRepository;

	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public List<Authority> getListOfAuthorities() {
		// TODO Auto-generated method stub
		return authorityRepository.findAll();
	}

	@Override
	public List<User> getListOfUsers() {
		// TODO Auto-generated method stub
		return userDetailsRepository.findAll();
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		Optional<User> old = userDetailsRepository.findById(user.getUserId());
		
		// if password is not equal as recorded 
		// it was updated manually and since it is plain text
		// should be encoded for storing in database
		if(old.isPresent())
		{
			if(!old.get().getPassword().equals(user.getPassword()))
			{
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
		}
		// this is a new customer
		// his password should be encoded
		else
		{
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		}
		
		
		
		userDetailsRepository.save(user);
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		userDetailsRepository.deleteById(id);
	}

	@Override
	public List<String> getUsersListOfAuthorities(String username) {
		// TODO Auto-generated method stub
		List<String> result = new ArrayList<String>();
		
		User user = userDetailsRepository.findByUserName(username);
		
		for(GrantedAuthority auth : user.getAuthorities())
		{
			result.add(auth.getAuthority());
		}
		
		
		return result;
	}
	
	
}
