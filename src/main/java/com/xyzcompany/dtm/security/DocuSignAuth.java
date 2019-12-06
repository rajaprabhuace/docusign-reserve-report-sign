package com.xyzcompany.dtm.security;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Date;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.algorithms.Algorithm;

public class DocuSignAuth {
	
	public static String createRs256JWT(
			String issuer,
			String subject,
			String ttlWindowHrs,
			String audience,
			String scope,
			String privatePEMKeyFile,
			String publicPEMKeyFile)
		throws IOException, GeneralSecurityException
	{
		System.out.println("issuer: " + issuer + "\n" +
				"subject: " + subject + "\n" +
				"expire time window in hrs: " + ttlWindowHrs + "\n" +
				"audience: " + audience + "\n" +
				"scope: " + scope + "\n" +
				"private key file: " + privatePEMKeyFile + "\n" +
				"public key file: " + publicPEMKeyFile);
		
		java.security.Security.addProvider(
		         new org.bouncycastle.jce.provider.BouncyCastleProvider()
		);		
		// Read private key
		RSAPrivateKey privateKey = getPrivateKey(privatePEMKeyFile);
		// Read public key
		RSAPublicKey publicKey = getPublicKey(publicPEMKeyFile);
		// Create RS256 algorithm
		Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
		// Get current time in ms since epoch
		long currentTimeMs = System.currentTimeMillis();
		Date issuedAt = new Date(currentTimeMs);
		// Get expiration time
		long expiryTimeMs = currentTimeMs + Integer.parseInt(ttlWindowHrs) * 3600000;
		Date expiresAt = new Date(expiryTimeMs);
		// Create JWT for impersonation
		String token = JWT.create()
				.withIssuer(issuer)
				.withSubject(subject)
				.withIssuedAt(issuedAt)
				.withExpiresAt(expiresAt)
				.withAudience(audience)
				.withClaim("scope", scope)
				.sign(algorithm);
		
		return token;
	}

	public static boolean validateToken(String token)
	{
		DecodedJWT jwt = JWT.decode(token);
		System.out.println("JWT header: " + jwt.getHeader());
		System.out.println("JWT issuer: " + jwt.getIssuer());
		System.out.println("JMT audience: " + jwt.getAudience());
		return true;
	}
	
	private static String getKey(String filename) 
			throws IOException {
	    // Read key from file
	    String strKeyPEM = "";
	    BufferedReader br = new BufferedReader(new FileReader(filename));
	    String line;
	    while ((line = br.readLine()) != null) {
	        strKeyPEM += line + "\n";
	    }
	    br.close();
	    return strKeyPEM;
	}
	
	public static String dummy(String str1, String str2, String str3, String str4, String str5, String str6, String str7)
	{
		return "dummyFunc" + "-" + str1 + "-" + str7;
	}
	
	public static RSAPrivateKey getPrivateKey(String filename) 
			throws IOException, GeneralSecurityException {
	    String privateKeyPEM = getKey(filename);
	    return getPrivateKeyFromString(privateKeyPEM);
	}	
	
	public static RSAPrivateKey getPrivateKeyFromString(String key) 
			throws IOException, GeneralSecurityException {
	    String privateKeyPEM = key;
	    privateKeyPEM = privateKeyPEM.replace("-----BEGIN RSA PRIVATE KEY-----\n", "");
	    privateKeyPEM = privateKeyPEM.replace("-----END RSA PRIVATE KEY-----", "");
	    //System.out.println("private key encoded:" + privateKeyPEM + "\n");
	    byte[] encoded = Base64.decodeBase64(privateKeyPEM);
	    KeyFactory kf = KeyFactory.getInstance("RSA");
	    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
	    RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(keySpec);
	    return privKey;
	}
	
	public static RSAPublicKey getPublicKey(String filename) 
			throws IOException, GeneralSecurityException {
	    String publicKeyPEM = getKey(filename);
	    return getPublicKeyFromString(publicKeyPEM);
	}
	
	public static RSAPublicKey getPublicKeyFromString(String key) 
			throws IOException, GeneralSecurityException {
	    String publicKeyPEM = key;
	    publicKeyPEM = publicKeyPEM.replace("-----BEGIN PUBLIC KEY-----\n", "");
	    publicKeyPEM = publicKeyPEM.replace("-----END PUBLIC KEY-----", "");
	    //System.out.println("public key encoded:" + publicKeyPEM + "\n");
	    byte[] encoded = Base64.decodeBase64(publicKeyPEM);
	    KeyFactory kf = KeyFactory.getInstance("RSA");
	    RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(encoded));
	    return pubKey;
	}
	
	public static void main(String args[])
	{
		try
		{
			String token = createRs256JWT(
					"c10492b5-6598-4ff2-979c-a4f8562f0dd3",
					"3d0e061c-dcc3-406c-89f3-7ad32d1cd4d2",
					"1", 
					"account-d.docusign.com",
					"signature impersonation organization_read group_read permission_read user_read user_write domain_read identity_provider_read",
					"/Users/ksoo/Documents/MuleSoft/PROJECTS/Murphy Oil/Document/Projects/DocuSign/DocuSign change user permission/private_key.txt",
					"/Users/ksoo/Documents/MuleSoft/PROJECTS/Murphy Oil/Document/Projects/DocuSign/DocuSign change user permission/public_key.txt");
			System.out.println("JWT Token: " + token);
			System.out.println("Validating token:");
			validateToken(token);
		}
		catch(IOException ioEx)
		{
			System.out.println("IOException");
			ioEx.printStackTrace();
		}
		catch(GeneralSecurityException gsEx)
		{
			System.out.println("GeneralSecurityException");
			gsEx.printStackTrace();
		}
		catch(Exception ex)
		{
			System.out.println("Exception");
			ex.printStackTrace();
		}
	}
}
