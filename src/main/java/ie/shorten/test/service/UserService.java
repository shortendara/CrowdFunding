package ie.shorten.test.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ie.shorten.test.entity.User;

@Service
public class UserService {
	List<User> list = new ArrayList<>();
	{
		list.add(new User(1, "Joe"));
		list.add(new User(2, "Jeff"));
		list.add(new User(3, "Jim"));
	}
	
	public List<User> getUsers() {
		return list;
	}
} 
