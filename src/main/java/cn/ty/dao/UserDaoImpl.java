package cn.ty.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import cn.ty.api.dao.UserDao;
import cn.ty.model.User;

public class UserDaoImpl implements UserDao {

	private SqlSessionTemplate sqlSession;  
	  
    /* (non-Javadoc) 
     * @see com.dahafo.um.dao.UserDao#insertUser(com.dahafo.um.entity.User) 
     */  
	@Override
    public void insertUser(User user) {  
        sqlSession.insert("insertUser", user);  
  
    }  
  
    /* (non-Javadoc) 
     * @see com.dahafo.um.dao.UserDao#findAll() 
     */  
	@Override
    public List<User> findAll() {  
        return sqlSession.selectList("selectAllUser");  
    }  
    public SqlSessionTemplate getSqlSession() {  
        return sqlSession;  
    }  
  
    public void setSqlSession(SqlSessionTemplate sqlSession) {  
        this.sqlSession = sqlSession;  
    }  

}
