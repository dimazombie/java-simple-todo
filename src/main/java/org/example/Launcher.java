package org.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;

public class Launcher {
    public static String PORT_ENV_NAME = "TODOAPP_PORT";
    public static int DEFAULT_PORT = 8080;

    public static void main(String[] args) throws Exception {
        int port = getPort();
        Server server = new Server(port);

        // Enable parsing of jndi-related parts of web.xml and jetty-env.xml
        Configuration.ClassList classlist = Configuration.ClassList.setServerDefault(server);
        classlist.addAfter("org.eclipse.jetty.webapp.FragmentConfiguration",
                "org.eclipse.jetty.plus.webapp.EnvConfiguration",
                "org.eclipse.jetty.plus.webapp.PlusConfiguration");

        WebAppContext context = new WebAppContext("webapp", "/");

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            // fix for Windows, so Jetty doesn't lock files
            context.getInitParams().put("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
        }

        server.setHandler(context);
        server.start();
    }

    public static int getPort() {
        String port = System.getenv(PORT_ENV_NAME);
        if(port == null || port.isEmpty()) {
            return DEFAULT_PORT;
        } else {
            return Integer.parseInt(port);
        }
    }
}