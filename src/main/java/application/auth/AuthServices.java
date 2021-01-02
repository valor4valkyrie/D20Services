package application.auth;

import com.google.common.flogger.FluentLogger;
import org.assertj.core.util.Lists;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthServices {

    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    private Environment env;

    @Autowired
    public AuthServices(Environment env) {
        this.env = env;
    }

    public boolean isAuthenticated(String jwt) {
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

        textEncryptor.setPassword(env.getProperty("security.jwt.password"));

        List<String> profileList = Lists.newArrayList(env.getActiveProfiles());

        if (!profileList.contains("dev")) {
            try {
                String decrypt = textEncryptor.decrypt(jwt);

                LocalDateTime tokenDate = LocalDateTime.parse(decrypt.substring(decrypt.indexOf("-") + 1));

                if (LocalDateTime.now().isAfter(tokenDate.plusSeconds(5))) {
                    logger.atSevere().log("Expired Token");
                    return false;
                }

            } catch (Exception e) {
                logger.atSevere().log("Error decrypting token");
                return false;
            }
        }

        return true;
    }

    public String encryptPassword(String password) {
        //TODO: Change this environment variable to a password encryption separate from JWT
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(env.getProperty("security.jwt.password"), 10000, 128);
        return encoder.encode(password);
    }

    public boolean isValidPassword(String pwd, String encodedPwd) {
        //TODO: Change this environment variable to a password encryption separate from JWT
        Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(env.getProperty("security.jwt.password"), 10000, 128);
        return encoder.matches(pwd, encodedPwd);
    }
}
