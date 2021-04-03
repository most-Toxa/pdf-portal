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

import net.dev4any1.model.CategoryModel;
import net.dev4any1.model.UserModel;
import net.dev4any1.service.CategoryServiceImpl;
import net.dev4any1.service.PublisherServiceImpl;
import net.dev4any1.service.UserServiceImpl;

@Path("/category")
@RequestScoped
public class CategoryResource {

	public final static Logger LOG = Logger.getLogger(CategoryResource.class.getName());

	@Inject
	public CategoryServiceImpl catService;
	@Inject
	public PublisherServiceImpl pubService;

	@Produces(MediaType.APPLICATION_XML)
	@POST
	@Path("/create/{name}")
	public Response create(@PathParam("name") String name) {
		if (catService.getByName(name) != null) {
			return Response.status(Response.Status.CONFLICT).entity("category " + name + " already exists").build();
		}
		CategoryModel category = catService.createCategory(name);
        LOG.info("category " + category.getName() + " was successfully created");
		return Response.status(Response.Status.OK).build();
	}
	
	
	
}