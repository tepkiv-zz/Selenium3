package tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URL;

public class isBugFixed implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        Bug bug = testMethod.getAnnotation(Bug.class);
        if (bug != null) {
            try {
                MantisConnectLocator mcl = new MantisConnectLocator();
                MantisConnectPortType mcp = mcl.getMantisConnectPort(
                        new URL("http://localhost/mantisbt/api/soap/mantisconnect.php"));
                IssueData issueData = mcp.mc_issue_get("administrator", "root", BigInteger.valueOf(bug.value()));
                String status = issueData.getStatus().getName();
                if (!("closed".equals(status) || "resolved".equals(status))) {
                    annotation.setEnabled(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
