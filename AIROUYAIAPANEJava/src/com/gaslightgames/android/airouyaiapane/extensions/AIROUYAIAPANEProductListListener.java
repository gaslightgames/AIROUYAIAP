package com.gaslightgames.android.airouyaiapane.extensions;

import java.util.ArrayList;

import com.adobe.fre.FREContext;

import android.os.Bundle;
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
		((AIROUYAIAPANEExtensionContext)context).notifyAIR( "PRODUCT_CANCEL", "cancelled" );
	}

	@Override
	public void onFailure( int errorCode, String errorMessage, Bundle bundle )
	{
		((AIROUYAIAPANEExtensionContext)context).notifyAIR( "PRODUCT_FAILURE", errorMessage );
	}

	@Override
	public void onSuccess( ArrayList<Product> products )
	{
		for( Product p : products )
		{
			String product = p.getIdentifier() + "," + p.getName() + "," + p.getPriceInCents();
			
			((AIROUYAIAPANEExtensionContext)context).notifyAIR( "PRODUCT_SUCCESS", product );
		}
	}
}
