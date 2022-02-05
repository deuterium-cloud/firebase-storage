package cloud.deuterium.firebase.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.InputStream;

/**
 * Created by Milan Stojkovic 01-Oct-2021
 */

@Component
public class FBInitialize {

    private static final String SERVICE_ACCOUNT_KEY_JSON = "serviceAccountKey.json";

    @PostConstruct
    public void initialize() {

        try {
            InputStream is = new ClassPathResource(SERVICE_ACCOUNT_KEY_JSON).getInputStream();

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(is))
                    .build();

            FirebaseApp.initializeApp(options);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
