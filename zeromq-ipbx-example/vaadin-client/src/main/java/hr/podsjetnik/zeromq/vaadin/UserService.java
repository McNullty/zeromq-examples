package hr.podsjetnik.zeromq.vaadin;

import com.vaadin.server.ServiceException;

public interface UserService {

  User login(String username, String password) throws ServiceException;
}
