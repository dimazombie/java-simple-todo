package org.example;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class LauncherIntegrationTest {

    @Test
    public void healthCheckShouldReturn200() throws Exception {
        Launcher.main(null);

        String url = "http://localhost:" + Launcher.getPort();
        HttpURLConnection http = (HttpURLConnection)new URL(url).openConnection();
        http.connect();

        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
    }
}
