package base.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.valves.AccessLogValve;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.tomcat.util.scan.StandardJarScanner;

import java.util.logging.Logger;

public class WebServer {

    Logger LOG = Logger.getLogger(WebServer.class.getName());

    public static void main(String[] args) {
        WebServer server = new WebServer();
        server.start();
    }

    private void start() {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.setBaseDir("");

        Host host = createHost();

        tomcat.getEngine().addChild(host);
        Context ctx = tomcat.addWebapp(host, "", "");

        StandardJarScanner scanner = (StandardJarScanner) ctx.getJarScanner();
        scanner.isScanAllDirectories();

        StandardRoot res = new StandardRoot(ctx);
        res.setCacheMaxSize(100 * 1024 * 1024);

        res.addPreResources(new DirResourceSet(res, "/WEB-INF/classes", "", "/"));
        res.addJarResources(new DirResourceSet(res, "/WEB-INF/lib", "", "/"));
        ctx.setResources(res);

        // 支持TOMCAT 管理下载文件
        LOG.info("Files path: ${Conf.DOWNLOAD_DIR.get()} map to : ${Conf.DOWNLOAD_CONTEXT_PATH.get()}");
    }

    private Host createHost() {
        StandardHost host = new StandardHost();
        host.setAppBase("my-teach-base");
        host.setName("localhost");
        host.setWorkDir("");
        host.setAutoDeploy(true);
        host.setDeployOnStartup(true);
        host.addValve(createValve());
        host.addAlias("localhost");

        return host;
    }

    private AccessLogValve createValve() {
        AccessLogValve valve = new AccessLogValve();
        valve.setDirectory("");
        valve.setEncoding("UTF-8");
        valve.isRotatable();
        valve.setFileDateFormat("yyyy-MM-dd");
        valve.isBuffered();
        valve.setPrefix("access_log_");
        valve.setSuffix(".txt");
        valve.setPattern("%h %l %u %t &quot;%r&quot; %s %b %D");

        return valve;
    }
}
