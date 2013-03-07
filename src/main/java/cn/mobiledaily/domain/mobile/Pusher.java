package cn.mobiledaily.domain.mobile;

import javapns.Push;
import javapns.communication.exceptions.CommunicationException;
import javapns.communication.exceptions.KeystoreException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Johnson
 * Date: 3/4/13
 * Time: 11:28 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class Pusher {
    private static final Logger LOGGER = LoggerFactory.getLogger(Pusher.class);

    @Value("${mobile.apns.keystore}")
    private String keyStore;
    @Value("${mobile.apns.keystorepassword}")
    private String keyStorePassword;

    public String getKeyStore() throws IOException {
        URL url = getClass().getClassLoader().getResource(keyStore);
        if (url == null) return null;
        return url.getPath();
    }

    public void push(final String message, final String serviceToken) {
        push(message, Collections.singletonList(serviceToken));
    }

    public void push(final String message, final List<String> serviceTokens) {
        try {
            Push.alert(message, getKeyStore(), keyStorePassword, false, serviceTokens);
        } catch (CommunicationException | KeystoreException | IOException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
