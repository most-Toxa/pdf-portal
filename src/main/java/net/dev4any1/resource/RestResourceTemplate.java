package net.dev4any1.resource;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.servlet.RequestScoped;

import net.dev4any1.model.UserModel;
import net.dev4any1.service.UserServiceImpl;

@Path("/rest")
@RequestScoped
public class RestResourceTemplate {

	public final static Logger LOG = Logger.getLogger(RestResourceTemplate.class.getName());

	@Inject
	public UserServiceImpl service;

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	@Path("/trace")
	public Response trace() {
		UserModel user = service.createSubscriber("gfg", "gfg");
		LOG.info(user.toString());
		return Response.status(Response.Status.OK).entity(user).build();
	}
}