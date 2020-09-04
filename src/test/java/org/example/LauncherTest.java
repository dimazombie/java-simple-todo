package org.example;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import static org.junit.Assert.assertEquals;


public class LauncherTest {
    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

    @Test
    public void appPortShouldBeLoadedFromEnv() {
        environmentVariables.set("TODOAPP_PORT", "1234");
        assertEquals(1234, Launcher.getPort());
    }
}
