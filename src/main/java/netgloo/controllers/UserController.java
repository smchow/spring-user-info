package netgloo.controllers;

import netgloo.models.User;
import netgloo.models.UserDao;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/user")
public class UserController {

  @Autowired
  private UserDao _userDao;

  @RequestMapping(value="/all")
  @ResponseBody
  //localhost:8080/user/all
  public String all() {
    List<User> userList;
    try {
      userList = _userDao.getAll();
    }
    catch(Exception ex) {
      return ex.getMessage();
    }
    String res = "Here is the user list:";
    for (int i = 0; i < userList.size(); i++) {
      System.out.println(i);
    User curUser = (User) userList.get(i);
    System.out.println(curUser.toString());
    res = res +  curUser.getName() + " - " + curUser.getEmail() ;
}
    return   res;
  }
  
  @RequestMapping(value="/delete")
  @ResponseBody
  //localhost:8080/user/delete?id=1
  public String delete(long id) {
    try {
      User user = new User(id);
      _userDao.delete(user);
    }
    catch(Exception ex) {
      return ex.getMessage();
    }
    return "User succesfully deleted!";
  }
  
  //http://localhost:8080/user/get-by-email?email=jeff@jeff.com
  @RequestMapping(value="/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId;
    try {
      User user = _userDao.getByEmail(email);
      userId = String.valueOf(user.getId());
    }
    catch(Exception ex) {
      return "User not found";
    }
    return "The user id is: " + userId;
  }

  //http://localhost:8080/user/save?email=nick@nick.com&name=nick
  @RequestMapping(value="/save")
  @ResponseBody
  public String create(String email, String name) {
    try {
      User user = new User(email, name);
      _userDao.save(user);
    }
    catch(Exception ex) {
      return ex.getMessage();
    }
    return "User succesfully saved!";
  }

} // class UserController
