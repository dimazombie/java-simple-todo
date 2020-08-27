package org.example;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.eclipse.jetty.http.HttpStatus;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;

public class LauncherTest {
    @Test
    public void shouldReturn200() throws Exception {
        Launcher.main(null);

        HttpURLConnection http = (HttpURLConnection)new URL("http://localhost:8080/").openConnection();
        http.connect();

        assertThat("Response Code", http.getResponseCode(), is(HttpStatus.OK_200));
    }
}
