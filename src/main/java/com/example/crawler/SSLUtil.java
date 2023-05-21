package com.example.crawler;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLUtil {
	public static void trustAllCertificates() throws Exception{
		TrustManager[] trustAllCerts = new TrustManager[]{
			new X509TrustManager() {
				public void checkClientTrusted(
					X509Certificate[] chain, String authType) throws CertificateException {}

				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {}

				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}
			}
		};

		SSLContext sslContext = SSLContext.getInstance("TLS");
		sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
	}
}
