package com.bh.at.utils;


import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import static com.bh.at.main.AppConfig.apiPaths;
import static com.bh.at.main.AppConfig.getEnvParam;

import static com.bh.at.tester.BaseTester.actions;


public final class ArgocdUtils {

  private final Logger LOG = LoggerFactory.getLogger(ArgocdUtils.class);



  public void getARGOCDLogs(String service, int sinceTimeInSeconds, String expectedResult) throws NoSuchAlgorithmException, KeyManagementException {
    if(getEnvParam("data_API/appURL", "").contains("qa.np-0000197.npause1")) {
      Poller.waitForSpecifiedTime(45000);

      SSLContext ctx = SSLContext.getInstance("TLS");
      ctx.init(new KeyManager[0], new TrustManager[]{new DefaultTrustManager()}, new SecureRandom());
      SSLContext.setDefault(ctx);
      HttpURLConnection connection = null;
      try {
        String charset = "UTF-8";
        String param = "appNamespace=argocd&container=" + service + "&namespace=aws-qa&follow=false&group=apps&kind=Deployment&resourceName=" + service + "&tailLines=100&sinceSeconds=" + sinceTimeInSeconds;
        String query = URLEncoder.encode(param, charset);
        URL url = new URL("https://argocd.np-0000197.npause1.bakerhughes.com" + "/api/v1/applications/" + service + "-s1e-qa-aws/logs" + "?" + query);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);


        BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
        StringBuilder sb = new StringBuilder();
        String output;
        String result;
        while ((output = br.readLine()) != null) {
          sb.append(output);
        }
        result = sb.toString();
        String res = result.replaceAll("\"", "").replace("\\", "");
        File resourceFolder = new File(System.getProperty("user.dir") + "/src/main/resources/");
        if (!resourceFolder.exists())
          resourceFolder.mkdir();
        File f = new File(System.getProperty("user.dir") + "/src/main/resources/ArgoLogs.json");
        if (f.exists())
          f.delete();
        f.createNewFile();

        PrintWriter pw = new PrintWriter(f);
        pw.print(res);
        pw.close();
        verifyArgocdLogs(res, expectedResult);
      } catch (Exception e) {
        LOG.info(e.getMessage());
      } finally {
        connection.disconnect();
      }
    }
  }

  private void verifyArgocdLogs(String result,String expectedResult){
    String[] exp = expectedResult.split(",");
    for(int i=0;i<exp.length;i++){
      Assert.assertTrue("Data not sent !!! No entry found in Argocd",result.contains(exp[i]));
    }
  }

  private static class DefaultTrustManager implements X509TrustManager {
    @Override
    public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
    }
    @Override
    public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
    }
    @Override
    public X509Certificate[] getAcceptedIssuers() {
      return null;
    }
  }

}
