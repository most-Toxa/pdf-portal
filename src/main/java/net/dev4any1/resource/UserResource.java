package net.dev4any1.resource;

import java.util.Optional;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

//import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

import net.dev4any1.model.UserModel;
import net.dev4any1.service.PublisherServiceImpl;
import net.dev4any1.service.UserServiceImpl;

@Path("/user")
@RequestScoped
public class UserResource {

	public final static Logger LOG = Logger.getLogger(UserResource.class.getName());

	@Inject
	public UserServiceImpl userService;
	@Inject
	public PublisherServiceImpl pubService;

	@Produces(MediaType.APPLICATION_XML)
	@POST
	@Path("/create/{login}/{password}")
	public Response create(@PathParam("login") String login, @PathParam("password") String password) {
		if (userService.getByLogin(login).isPresent()) {
			return Response.status(Response.Status.CONFLICT).entity(login + " user already exists").build();
		}
		UserModel user = userService.createSubscriber(login, password);
        LOG.info("user " + user.getLogin() + " was successfully created");
		return Response.status(Response.Status.OK).entity(user).build();
	}
	
	@Produces(MediaType.APPLICATION_XML)
	@POST
	@Path("/grant/{login}/{name}")
	public Response grantPublisher(@PathParam("login") String login, @PathParam("name") String name) {
		Optional<UserModel> user = userService.getByLogin(login);
		if (user.isEmpty()) {
			return Response.status(Response.Status.CONFLICT).entity(login + " does not exists").build();
		}
		pubService.createPublisher(name, user.get());
        LOG.info("user " + user.get().getLogin() + " was successfully granted as publisher " + name);
		return Response.status(Response.Status.OK).entity(user).build();
	}
	
}