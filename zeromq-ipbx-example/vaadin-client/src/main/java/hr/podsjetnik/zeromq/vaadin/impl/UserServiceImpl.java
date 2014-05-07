package hr.podsjetnik.zeromq.vaadin.impl;

import hr.podsjetnik.zeromq.vaadin.User;
import hr.podsjetnik.zeromq.vaadin.UserService;

import com.vaadin.server.ServiceException;

public class UserServiceImpl implements UserService {
  @Override
  public User login(final String username, final String password) throws ServiceException {
    return new User(username, password);
  }
}
