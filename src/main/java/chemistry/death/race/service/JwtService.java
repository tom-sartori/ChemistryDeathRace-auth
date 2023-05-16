package chemistry.death.race.service;

import io.smallrye.jwt.build.Jwt;

import javax.inject.Singleton;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Singleton
public class JwtService {

    /**
     * Generate a JWT token.
     *
     * @param roles the roles to include in the token.
     * @return the generated token.
     */
    public String generateJwt(Set<String> roles) {
        return Jwt
                .issuer("ChemistryDeathRace-jwt")
                .subject("ChemistryDeathRace-app")
                .groups(roles)
                .expiresAt(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()) + (60 * 60 * 24))
                .sign();
    }
}
