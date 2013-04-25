package com.gaslightgames.android.airouyaiapane.extensions;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONObject;

import tv.ouya.console.api.Product;
import tv.ouya.console.api.Purchasable;

import android.util.Base64;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AIROUYAIAPANEMakeProdPurchase implements FREFunction
{

	@Override
	public FREObject call( FREContext context, FREObject[] passedArgs )
	{
		try
		{
			// Create a Product - from the parameters passed
			FREObject fro = passedArgs[0];
			String identifier = fro.getAsString();
			fro = passedArgs[1];
			String name = fro.getAsString();
			fro = passedArgs[2];
			int priceInCents = fro.getAsInt();
			
			Product product = new Product();
			product.setIdentifier( identifier );
			product.setName( name );
			product.setPriceInCents( priceInCents );
			
			SecureRandom sr = SecureRandom.getInstance( "SHA1PRNG" );
			
			String uniqueId = Long.toHexString( sr.nextLong() );
			
			JSONObject purchaseRequestJson = new JSONObject();
			purchaseRequestJson.put( "uuid", uniqueId );
			purchaseRequestJson.put( "identifier", identifier );
			purchaseRequestJson.put( "testing", true );			// *** RE-ADD OUR "TESTING" METHOD AND SET A PUBLIC BOOLEAN FLAG
																//	   CHANGE THE true TO THIS PUBLIC FLAG (FROM CONTEXT) ***
			
			String purchaseRequest = purchaseRequestJson.toString();
			
			byte[] keyBytes = new byte[16];
			sr.nextBytes( keyBytes );
			SecretKey key = new SecretKeySpec( keyBytes, "AES" );
			
			byte[] ivBytes = new byte[16];
			sr.nextBytes( ivBytes );
			IvParameterSpec iv = new IvParameterSpec( ivBytes );
			
			Cipher cipher = Cipher.getInstance( "AES/CBC/PKCS5Padding", "BC" );
			cipher.init( Cipher.ENCRYPT_MODE, key, iv );
			byte[] payload = cipher.doFinal( purchaseRequest.getBytes( "UTF-8" ) );
			
			cipher = Cipher.getInstance( "RSA/ECB/PKCS1Padding", "BC" );
			cipher.init( Cipher.ENCRYPT_MODE, ((AIROUYAIAPANEExtensionContext)context).publicKey );
			byte[] encryptedKey = cipher.doFinal( keyBytes );
			
			Purchasable purchasable = new Purchasable( identifier,
													   Base64.encodeToString( encryptedKey, Base64.NO_WRAP ),
													   Base64.encodeToString( ivBytes, Base64.NO_WRAP ),
													   Base64.encodeToString( payload, Base64.NO_WRAP ) );
			
			synchronized( ((AIROUYAIAPANEExtensionContext)context).outstandingPurchaseRequests )
			{
				((AIROUYAIAPANEExtensionContext)context).outstandingPurchaseRequests.put( uniqueId, product );
			}
			
			AIROUYAIAPANEPurchaseListener listener = new AIROUYAIAPANEPurchaseListener( context, product );
			
			((AIROUYAIAPANEExtensionContext)context).ouyaFacade.requestPurchase( purchasable, listener );
			
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
			
			((AIROUYAIAPANEExtensionContext)context).notifyAIR( "PURCHASE_FAILURE", "Error making Purchase," + exception.getMessage() );
		}
		
		return null;
	}

}
