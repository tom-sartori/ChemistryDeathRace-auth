package chemical.pursuit.repository;

import chemical.pursuit.collection.user.User;
import chemical.pursuit.constant.Roles;
import chemical.pursuit.service.AESCryptService;
import chemical.pursuit.service.JwtService;
import io.quarkus.mongodb.panache.PanacheMongoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<User> {
    @Inject
    JwtService jwtService;

    @Inject
    AESCryptService aesCryptService;

    public User findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public Response register(User user) {
        if (user.getEmail() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("User must provide an email. ")
                    .build();
        }
        else if (user.getPassword() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("User must provide a password. ")
                    .build();
        }
        else if (findByEmail(user.getEmail()) != null) {
            return Response.status(Response.Status.CONFLICT)
                    .entity("User already exists. ")
                    .build();
        }
        else {
            user.setId(null);
            user.setAdmin(false);
            try {
                user.setPassword(aesCryptService.encrypt(user.getPassword()));
            } catch (Exception e) {
                return Response
                        .status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity("Error encrypting password. ")
                        .build();
            }
            persist(user);
            return Response
                    .status(Response.Status.CREATED)
                    .entity(user)
                    .build();
        }
    }

    public Response login(User user) {
        if (user.getEmail() == null || user.getPassword() == null) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity("Bad credentials. ")
                    .build();
        }
        else {
            User supposedUser = findByEmail(user.getEmail());
            if (supposedUser == null) {
                return Response
                        .status(Response.Status.UNAUTHORIZED)
                        .entity("Bad credentials. ")
                        .build();
            }
            else {
                try {
                    if (aesCryptService.decrypt(supposedUser.getPassword()).equals(user.getPassword())) {
                        Set<String> roles = new HashSet<>();
                        if (supposedUser.isAdmin()) {
                            roles.addAll(Arrays.asList(Roles.ADMIN, Roles.CONTRIBUTOR));
                        }
                        else {
                            roles.add(Roles.CONTRIBUTOR);
                        }
                        return Response
                                .status(Response.Status.OK)
                                .entity("{ \"token\": \"" + jwtService.generateJwt(roles) + "\" }")
                                .build();
                    }
                    else {
                        return Response
                                .status(Response.Status.UNAUTHORIZED)
                                .entity("Bad credentials. ")
                                .build();
                    }
                } catch (Exception e) {
                    return Response
                            .status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity("Error decrypting password. ")
                            .build();
                }
            }
        }
    }
}
