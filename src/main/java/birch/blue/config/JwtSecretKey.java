package birch.blue.config;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;

@Configuration
public class JwtSecretKey {
	@Value("${jwt.auth.secret_key}")
	private String secretKey;

	@Bean
	public SecretKey secretKey() {
		return Keys.hmacShaKeyFor(this.secretKey.getBytes());
	}
}
