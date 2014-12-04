package org.mockserver;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.echo.EchoServer;
import org.mockserver.integration.server.AbstractClientServerIntegrationTest;
import org.mockserver.socket.PortFactory;

/**
 * @author jamesdbloom
 */
public class ClientServerMavenPluginTest extends AbstractClientServerIntegrationTest {

    private final static int ECHO_SERVER_HTTP_PORT = PortFactory.findFreePort();
    private final static int SERVER_HTTP_PORT = 8092;
    private final static int SERVER_HTTPS_PORT = 8093;
    private static EchoServer echoServer;

    @BeforeClass
    public static void createClient() throws Exception {
        echoServer = new EchoServer(ECHO_SERVER_HTTP_PORT);
        mockServerClient = new MockServerClient("localhost", SERVER_HTTP_PORT, servletContext);
    }

    @AfterClass
    public static void stopServer() throws Exception {
        echoServer.stop();
    }

    @Before
    public void clearServer() {
        mockServerClient.reset();
    }

    @Override
    public int getMockServerPort() {
        return SERVER_HTTP_PORT;
    }

    @Override
    public int getMockServerSecurePort() {
        return SERVER_HTTPS_PORT;
    }

    @Override
    public int getTestServerPort() {
        return ECHO_SERVER_HTTP_PORT;
    }

}