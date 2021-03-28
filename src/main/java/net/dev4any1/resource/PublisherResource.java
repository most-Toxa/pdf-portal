package net.dev4any1.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataParam;

//import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

import net.dev4any1.model.JournalModel;
import net.dev4any1.model.UserModel;
import net.dev4any1.service.JournalServiceImpl;
import net.dev4any1.service.UserServiceImpl;

@Path("/publisher")
@RequestScoped
public class PublisherResource {

	public final static Logger LOG = Logger.getLogger(PublisherResource.class.getName());

	@Inject
	public UserServiceImpl service;

	
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_XML)
	@POST
	@Path("/uploadFile")
	public Response uploadFile(@FormDataParam("file") InputStream uploadedInputStream) throws FileNotFoundException {
		String fileName = "/mnt/c/Users/robot.testfile.txt";
		saveToFile(uploadedInputStream, fileName);
		LOG.info("file " + fileName + " was successfully uploaded");
		return Response.status(Response.Status.OK).entity("ok").build();
	}

	private void saveToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

		try {
			OutputStream out = null;
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}