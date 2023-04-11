package chemical.pursuit.service;

import io.smallrye.jwt.build.Jwt;

import javax.inject.Singleton;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Singleton
public class JwtService {

    public String generateJwt(Set<String> roles) {
        return Jwt
                .issuer("ChemicalPursuit-jwt")
                .subject("ChemicalPursuit-app")
                .groups(roles)
                .expiresAt(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + (60 * 60 * 24))
                .sign();
    }
}
