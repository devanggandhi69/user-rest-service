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

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.hibernet.util.HibernateUtil;
import com.hibernet.vo.User;

@Path("/user")
public class UserResource {

	@GET
	@Path("/{user_name}/{c_name}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void saveUser(@PathParam("user_name") String name, @PathParam("c_name") String cname) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		User user = new User();

		user.setUsername(name);
		user.setCreatedBy(cname);
		user.setCreatedDate(new Date(0));

		session.save(user);
		session.getTransaction().commit();
		// if (!session.getTransaction().wasCommitted()) {
		// session.getTransaction().commit();
		// }
		session.close();
	}

	@GET
	@Path("/getAll")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User getAllUser() {
		User user = new User();
		Session sess = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			List list = sess.createQuery("from User").list();
			Iterator itr = list.iterator();
			while (itr.hasNext()) {
				user = (User) itr.next();
				user.setUserId((int) list.get(1));
				user.setUsername((String) list.get(2));
				user.setCreatedBy((String) list.get(3));
				user.setCreatedDate((Date) list.get(4));
				return user;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			sess.close();
		}
		return user;
	}

}
