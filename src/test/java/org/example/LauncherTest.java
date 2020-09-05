package org.example;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import static org.junit.Assert.assertEquals;


public class LauncherTest {
    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

    @Test
    public void portShouldBeLoadedFromEnv() {
        environmentVariables.set(Launcher.PORT_ENV_NAME, "1234");
        assertEquals(1234, Launcher.getPort());
    }

    @Test
    public void defaultPortShouldBeUsedIfNoEnv() {
        environmentVariables.clear(Launcher.PORT_ENV_NAME);
        assertEquals(Launcher.DEFAULT_PORT, Launcher.getPort());
    }
}
