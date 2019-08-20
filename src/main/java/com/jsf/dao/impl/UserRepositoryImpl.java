package com.jsf.dao.impl;

import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
//import org.h2.tools.RunScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jsf.dao.AbstractJpaRepository;
import com.jsf.dao.UserRepository;
import com.jsf.model.User;

@Repository
public class UserRepositoryImpl extends AbstractJpaRepository<User, Integer> implements UserRepository {

	private static final Logger logger = LoggerFactory.getLogger(UserRepositoryImpl.class);
	
	@Autowired
	private DataSource dataSource;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public User saveUser(User user) {
		User u = this.create(user);
		logger.info("User saved successfully, User Details: " + u);
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public List<User> listUser() {
		//testDb();
		
		// DataSource
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
				
		try {
			conn = dataSource.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(" select * from users ");
			
			System.out.println("Datasource: ");
			while (rs.next()) {
				System.out.println("Id: " + rs.getInt("user_id") + " Name: " + rs.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		// Criteria
		//Session session = (Session) this.getEntityManager().getDelegate();
		
		//Transaction transaction = session.getTransaction();
		
		//CriteriaBuilder builder = session.getCriteriaBuilder();
//		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
//		
//		CriteriaQuery<String> query = builder.createQuery(String.class);
//		Root<User> root = query.from(User.class);
//		
//		query.select(root.get("email"));
//        Query<String> q = session.createQuery(query);
//        
//        List<String> list = q.getResultList();
//        
//        System.out.println("Criteria: ");
//        
//        for (String name : list) {
//           System.out.println(name);
//        }

        //transaction.commit();
        
        
        
        
        // Entity Manager
		return this.getEntityManager().createQuery("from User").getResultList();
		//return new ArrayList<User>();
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public void deleteUser(User user) {
		this.delete(user);
		logger.info("User removed successfully, User Details: " + user);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		return this.update(user);
	}

}