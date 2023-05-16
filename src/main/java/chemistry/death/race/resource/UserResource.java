package chemistry.death.race.resource;

import chemistry.death.race.collection.user.User;
import chemistry.death.race.constant.Paths;
import chemistry.death.race.repository.UserRepository;

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

    /**
     * Register a new user. The email and password must be provided.
     *
     * @param user the user to register. Values are encrypted.
     * @return 201 if successful, 400 if email or password is missing.
     */
    @POST
    @Path(Paths.REGISTER)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        return userRepository.register(user);
    }

    /**
     * Login a user. The email and password must be provided.
     *
     * @param user the user to login. Values are encrypted.
     * @return 200 if successful, 400 if email or password is missing.
     */
    @POST
    @Path(Paths.LOGIN)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(User user) {
        return userRepository.login(user);
    }

    /**
     * Read all users.
     *
     * @return 200 if successful.
     */
    @GET
    @Consumes(MediaType.MEDIA_TYPE_WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public Response read() {
        return Response
                .status(Response.Status.OK)
                .entity(userRepository.listAll())
                .build();
    }

    /**
     * Enable the API.
     *
     * @return 200 if successful.
     */
    @GET
    @Path(Paths.ENABLE)
    @Consumes(MediaType.MEDIA_TYPE_WILDCARD)
    @Produces(MediaType.APPLICATION_JSON)
    public Response enable() {
        return Response
                .status(Response.Status.OK)
                .entity("{\"enable\": \"API enabled\"}")
                .build();
    }
}
