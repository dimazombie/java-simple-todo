package org.example;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;

public class LauncherIntegrationTest {
    @Test
    public void shouldReturn200() throws Exception {
        String url = "http://localhost:" + Launcher.getPort();

        Launcher.main(null);
        HttpURLConnection http = (HttpURLConnection)new URL(url).openConnection();
        http.connect();

        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
    }


}
