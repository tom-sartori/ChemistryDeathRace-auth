package exception;

import io.quarkus.logging.Log;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException exception) {
        Log.error(Arrays.toString(exception.getStackTrace()));
        return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_PLAIN).entity(exception.getMessage()).build();
    }
}
