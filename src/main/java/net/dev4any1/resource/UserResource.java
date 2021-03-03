package net.dev4any1.resource;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

import net.dev4any1.model.UserModel;
import net.dev4any1.service.UserServiceImpl;

@Path("/user")
@RequestScoped
public class UserResource {

	public final static Logger LOG = Logger.getLogger(UserResource.class.getName());

	@Inject
	public UserServiceImpl service;

	@Produces(MediaType.APPLICATION_XML)
	@GET //TODO
	@Path("/create/{login}/{password}")
	public Response trace (@PathParam("login") String login, @PathParam("password") String password) {
		UserModel user = service.createSubscriber(login, password);
		LOG.info(user.toString());
		LOG.info(user.hashCode() + "");
		return Response.status(Response.Status.OK).entity(user).build();
	} 
}