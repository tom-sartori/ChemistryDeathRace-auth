package chemical.pursuit.resource;

import chemical.pursuit.collection.user.User;
import chemical.pursuit.constant.Paths;
import chemical.pursuit.repository.UserRepository;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(Paths.USER)
@ApplicationScoped
public class UserResource {

    @Inject
    UserRepository userRepository;

    @POST
    @Path(Paths.REGISTER)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        return userRepository.register(user);
    }

    @POST
    @Path(Paths.LOGIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        return userRepository.login(user);
    }

    @GET
    @Consumes(MediaType.MEDIA_TYPE_WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public Response read() {
        return Response
                .status(Response.Status.OK)
                .entity(userRepository.listAll())
                .build();
    }
}
