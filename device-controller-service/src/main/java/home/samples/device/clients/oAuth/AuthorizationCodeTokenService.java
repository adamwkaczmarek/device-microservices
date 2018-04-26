package home.samples.device.clients.oAuth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class AuthorizationCodeTokenService {

    public static final String AUTHENTICATION_SERVICE_URL = "http://authentication-service/auth/oauth/token";

    @Value("${security.jwt.client-id}")
    private String clientId;

    @Value("${security.jwt.client-secret}")
    private String clientSecret;



    @Autowired
    private AuthorizationCodeConfiguration configuration;

    @Autowired
    @Qualifier("empty")
    private RestTemplate restTemplate;



    public OAuth2Token getToken(String username,String password){
        String authBase64 = configuration.encodeCredentials(clientId,
                clientSecret);

        RequestEntity<MultiValueMap<String, String>> requestEntity = new RequestEntity<>(
            configuration.getBody(username,password),
            configuration.getHeader(authBase64), HttpMethod.POST,
            URI.create(AUTHENTICATION_SERVICE_URL));

        ResponseEntity<OAuth2Token> responseEntity = restTemplate.exchange(
                requestEntity, OAuth2Token.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }

        throw new RuntimeException("error trying to retrieve access token");
    }
}
