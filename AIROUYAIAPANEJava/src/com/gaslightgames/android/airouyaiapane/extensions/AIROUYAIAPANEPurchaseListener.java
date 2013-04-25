package com.gaslightgames.android.airouyaiapane.extensions;

import org.json.JSONObject;

import com.adobe.fre.FREContext;

import android.os.Bundle;
import tv.ouya.console.api.OuyaEncryptionHelper;
import tv.ouya.console.api.OuyaErrorCodes;
import tv.ouya.console.api.OuyaResponseListener;
import tv.ouya.console.api.Product;

public class AIROUYAIAPANEPurchaseListener implements OuyaResponseListener<String>//<Product>
{
	private FREContext context;
	private Product mProduct;
	
	public AIROUYAIAPANEPurchaseListener( FREContext context, final Product product )
	{
		this.context = context;
		this.mProduct = product;
	}

	@Override
	public void onCancel()
	{
		((AIROUYAIAPANEExtensionContext)context).notifyAIR( "PURCHASE_CANCEL", "purchase cancelled" );
	}

	@Override
	public void onFailure( int errorCode, String errorMessage, Bundle bundle )
	{
		((AIROUYAIAPANEExtensionContext)context).notifyAIR( "PURCHASE_FAILURE", errorMessage );
	}

	@Override
	public void onSuccess( String response )
	{
		Product product;
		
		try
		{
			OuyaEncryptionHelper helper = new OuyaEncryptionHelper();
			
			JSONObject jsonResponse = new JSONObject( response );
			
			if( jsonResponse.has( "key" ) && jsonResponse.has( "iv" ) )
			{
				String id = helper.decryptPurchaseResponse( jsonResponse, ((AIROUYAIAPANEExtensionContext)context).publicKey );
				Product storedProduct;
				synchronized( ((AIROUYAIAPANEExtensionContext)context).outstandingPurchaseRequests )
				{
					storedProduct = ((AIROUYAIAPANEExtensionContext)context).outstandingPurchaseRequests.remove( id );
				}
				if( null == storedProduct )
				{
					onFailure( OuyaErrorCodes.THROW_DURING_ON_SUCCESS,
							   "No purchase outstanding for the given purchase request",
							   Bundle.EMPTY );
					return;
				}
				
				product = storedProduct;
			}
			else
			{
				product = new Product( new JSONObject( response ) );
				
				if( !mProduct.getIdentifier().equals( product.getIdentifier() ) )
				{
					onFailure( OuyaErrorCodes.THROW_DURING_ON_SUCCESS,
							   "Purchased product is not the same as purchase request product",
							   Bundle.EMPTY );
					return;
				}
			}
			
			// Build the product information into a String format to send back to AIR
			String prod = "";
			prod += product.getIdentifier() + ",";
			prod += product.getPriceInCents() + ",";
			prod += product.getName() + ",";
			
			((AIROUYAIAPANEExtensionContext)context).notifyAIR( "PURCHASE_SUCCESS", prod );
		}
		catch( Exception exception )
		{
			((AIROUYAIAPANEExtensionContext)context).notifyAIR( "PURCHASE_FAILURE", exception.getMessage() );
		}
	}

}
