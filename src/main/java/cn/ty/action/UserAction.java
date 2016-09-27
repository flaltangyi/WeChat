package cn.ty.action;

import com.opensymphony.xwork2.ActionSupport;

import cn.ty.api.service.UserService;
import cn.ty.model.User;

public class UserAction extends ActionSupport {

    private UserService userService;  
    private User user=null;  
      
    /* (non-Javadoc) 
     * @see com.opensymphony.xwork2.ActionSupport#execute() 
     */  
    @Override  
    public String execute() throws Exception {  
        user=new User();  
        user.setName("玩哈哈");  
        user.setAge(532);  
        System.out.println("------------到这里了");  
        userService.newUser(user);  
        return "success";  
    }  
      
       
    public UserService getUserService() {  
        return userService;  
    }  
  
    public void setUserService(UserService userService) {  
        this.userService = userService;  
    }  
  
    public User getUser() {  
        return user;  
    }  
  
    public void setUser(User user) {  
        this.user = user;  
    }     

}
