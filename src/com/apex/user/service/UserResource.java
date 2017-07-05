package com.apex.user.service;

import javax.ws.rs.PathParam;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.hibernet.util.HibernateUtil;
import com.hibernet.vo.User;

@Path("/user")
public class UserResource {

	@GET
	@Path("/add/{user_name}/{c_name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User saveUser(@PathParam("user_name") String name, @PathParam("c_name") String cname) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		User user = new User();

		user.setUsername(name);
		user.setCreatedBy(cname);
		user.setCreatedDate(new Date(0));
		session.save(user);
		session.getTransaction().commit();
	
		session.close();

		return user;
	}

	@GET
	@Path("/getAll")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void getAllUser() {

		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try{
			tx = sess.beginTransaction();
			List list = sess.createQuery("from User").list();

			//////////////
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				User user = (User) iterator.next();
				System.out.print("First Name: " + user.getUserId());
				System.out.print("  Last Name: " + user.getUsername());
				System.out.println("  Salary: " + user.getCreatedBy());
				System.out.println("  Salary: " + user.getCreatedDate());
			}
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	      }finally {
	         sess.close(); 
	      }
	   }

	@GET
	@Path("/delete/{id}")
	@Produces({ MediaType.APPLICATION_XML })
	public void delUser(@PathParam("id") Integer userId) {
		Session sess = (Session) HibernateUtil.getSessionFactory();
		User user = new User();
		user.setUserId(userId);
		try {
			((SessionFactory) sess).openSession();
			sess.delete(user);
			System.out.println("Row deleted which has id =" + userId);
		} catch (Exception e) {
			sess.getTransaction().rollback();
			e.printStackTrace();
		}
	}

}
