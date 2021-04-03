package net.dev4any1.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;

//import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

import net.dev4any1.model.CategoryModel;
import net.dev4any1.model.UserModel;
import net.dev4any1.pojo.Role;
import net.dev4any1.service.CategoryServiceImpl;
import net.dev4any1.service.JournalServiceImpl;
import net.dev4any1.service.PublisherServiceImpl;
import net.dev4any1.service.UserServiceImpl;

@Path("/publisher")
@RequestScoped
public class PublisherResource {

	public final static Logger LOG = Logger.getLogger(PublisherResource.class.getName());

	@Inject
	public UserServiceImpl userService;
	@Inject
	public CategoryServiceImpl catService;
	@Inject
	public JournalServiceImpl journalService;
	@Inject
	public PublisherServiceImpl pubService;

	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_XML)
	@POST
	@Path("/uploadFile/{login}/{category}")
	public Response uploadFile(@PathParam("login") String login, @PathParam("category") String category,  
			@FormDataParam("file") InputStream uploadedInputStream) throws FileNotFoundException {
		String fileName = System.getProperty("user.dir") + "/target/" + UUID.randomUUID().toString() + ".pdf";
		saveToFile(uploadedInputStream, fileName);
		LOG.info("current path is " + System.getProperty("user.dir") + " login " + login + " category " + category);
		LOG.info("file " + fileName + " was successfully uploaded");
		CategoryModel cat = catService.getByName(category);
		if (cat == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("category " + category + " does not exist").build();	
		}
		Optional<UserModel> user = userService.getByLogin(login);
		if (user.isEmpty()) {
			return Response.status(Response.Status.BAD_REQUEST).entity("user " + user + " does not exist").build();
		}
		if (!user.get().getRole().equals(Role.PUBLISHER.name())) {
			return Response.status(Response.Status.BAD_REQUEST).entity("user " + user + " is not a publisher").build();
		}
		journalService.publish(pubService.getPublisher(user.get()), fileName, cat.getId(), new Date(System.currentTimeMillis()));
		return Response.status(Response.Status.OK).build();	
	}

	private void saveToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try {
			OutputStream out = null;
			int theByte = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((theByte = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, theByte);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}