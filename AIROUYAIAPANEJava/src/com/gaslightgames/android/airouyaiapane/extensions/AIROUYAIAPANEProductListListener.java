package com.gaslightgames.android.airouyaiapane.extensions;

import java.util.ArrayList;

import com.adobe.fre.FREContext;

import android.os.Bundle;
import android.util.Log;
import tv.ouya.console.api.OuyaResponseListener;
import tv.ouya.console.api.Product;

public class AIROUYAIAPANEProductListListener implements OuyaResponseListener<ArrayList<Product>>
{
	private FREContext context;
	
	public AIROUYAIAPANEProductListListener( FREContext context )
	{
		this.context = context;
	}
	
	@Override
	public void onCancel()
	{
		Log.i( "IAP_PRODUCT_LIST_LISTENER", "Cancelled." );
		
		((AIROUYAIAPANEExtensionContext)context).notifyProduct( "PRODUCT_CANCEL", "cancelled" );
	}

	@Override
	public void onFailure( int errorCode, String errorMessage, Bundle bundle )
	{
		Log.i( "IAP_PRODUCT_LIST_LISTENER", "Error: " + errorMessage );
		
		((AIROUYAIAPANEExtensionContext)context).notifyProduct( "PRODUCT_FAILURE", errorMessage );
	}

	@Override
	public void onSuccess( ArrayList<Product> products )
	{
		for( Product p : products )
		{
			Log.i( "IAP_PRODUCT_LIST_LISTENER", "Product: " + p.getName() + ", Price: " + p.getPriceInCents() );
			String product = p.getIdentifier() + "," + p.getName() + "," + p.getPriceInCents();
			
			((AIROUYAIAPANEExtensionContext)context).notifyProduct( "PRODUCT_SUCCESS", product );
		}
	}
}
