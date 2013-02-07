package com.gaslightgames.android.airouyaiapane.extensions;

import com.adobe.fre.FREContext;

import android.os.Bundle;
import android.util.Log;
import tv.ouya.console.api.OuyaResponseListener;

public class AIROUYAIAPANEPurchaseListener implements OuyaResponseListener<String>//<Product>
{
	private FREContext context;
	
	public AIROUYAIAPANEPurchaseListener( FREContext context )
	{
		this.context = context;
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
	public void onSuccess( String product )
	{
		String prod = product;
		//{"identifier":"test","priceInCents":100,"name":"Test Product"}
		// Remove the string "{\"identifier\":"
		prod = prod.replace( "{\"identifier\":", "" );
		// Remove the string "\"priceInCents\":"
		prod = prod.replace( "\"priceInCents\":", "" );
		// Remove the string "\"name\":"
		prod = prod.replace( "\"name\":", "" );
		// Remove the trailing "}"
		prod = prod.replace( "}", "" );
		// Remove all "quotes"
		prod = prod.replaceAll( "\"", "" );
		// Use the string as is
		
		Log.i( "PURCHASE_LISTENER", prod );
			
		((AIROUYAIAPANEExtensionContext)context).notifyAIR( "PURCHASE_SUCCESS", prod );
	}

}
