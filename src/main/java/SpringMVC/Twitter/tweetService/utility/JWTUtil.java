package SpringMVC.Twitter.tweetService.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTUtil {
    private String ACCESS_TOKEN_SECRET_KEY = "ksdyfhskjvbsfuyhsbksfjhgfsgjkflsbgs";

    // Function that grabs jwt token from Authorization header and validates it
    public boolean isAccessTokenValid(String access_token, String userId) {
        try {
            String jwtToken = getTokenFromHeader(access_token);
            String subject = getSubjectFromToken(jwtToken);

            return (userId.equals(subject) && isTokenValid(jwtToken));
        } catch (JWTVerificationException exception){
            System.out.println("Error while validating token: " + exception);
            return false;
        }
    }

    // Function that grabs jwt token from Authorization header and returns the subject
    public String decodeSubjectToken(String access_token) {
        DecodedJWT jwt = null;
        String jwtToken = getSubjectFromToken(access_token);

        try {
            jwt = JWT.decode(jwtToken);
            return jwt.getSubject();
        } catch (JWTDecodeException exception){
            System.out.println("Error while decoding token: " + exception);
            return null;
        }
    }

    /*
     * Private utility functions
     */
    private String getSubjectFromToken(String jwtToken) {
        DecodedJWT jwt = null;

        try {
            jwt = JWT.decode(jwtToken);
            return jwt.getSubject();
        } catch (JWTDecodeException exception){
            System.out.println("Error while extracting subject from token. Error: " + exception);
            return null;
        }
    }

    private boolean isTokenValid(String jwtToken) {
        try {
            Date currentTime = new Date();
            Date tokenExpirationTime = getExpirationDateFromToken(jwtToken);

            return currentTime.before(tokenExpirationTime);
        } catch (Exception e) {
            System.out.println("Error while validating token. Error: " + e);
            return false;
        }
    }

    private boolean isTokenExpired(String jwtToken) {
        try {
            Date tokenExpirationDate = getExpirationDateFromToken(jwtToken);

            return tokenExpirationDate.before(new Date());
        } catch (Exception e) {
            System.out.println("Error while validating token. Error: " + e);
            return false;
        }
    }

    private Date getExpirationDateFromToken(String jwtToken) {
        DecodedJWT jwt = null;

        try {
            jwt = JWT.decode(jwtToken);

            return jwt.getExpiresAt();
        } catch (JWTDecodeException exception) {
            System.out.println("Error while extracting expiration date from token. Error: " + exception);
            return null;
        }
    }

    private String getTokenFromHeader(String token) {
        String tokens[] = token.split(" ");

        return tokens[1].substring(0, tokens[1].length() - 1);
    }

    // This method is here just for an example
    private String getAccessToken(String userId) {
        String token = "";

        try {
            Algorithm algorithm = Algorithm.HMAC256(ACCESS_TOKEN_SECRET_KEY);
            token = JWT.create().withSubject(userId).sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            System.out.println("Error while creating token: " + exception);
            return  null;
        }
    }
}
