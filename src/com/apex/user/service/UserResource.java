package com.apex.user.service;

import javax.ws.rs.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.apex.user.vo.UserVO;

@Path("/user")
public class UserResource {

	@GET
	@Path("/{user_id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public UserVO getUser(@PathParam("user_id") int id) {

		UserVO userVO = new UserVO();
		userVO.setId(id);
		userVO.setFirstname("Devang");
		userVO.setMiddlename("P");
		userVO.setLastname("Gandhi");

		return userVO;
	}

}
