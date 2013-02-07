package com.gaslightgames.android.airouyaiapane.extensions;

import tv.ouya.console.api.Purchasable;

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
			FREObject fro = passedArgs[0];
			String product = fro.getAsString();
			
			Purchasable productToBuy = new Purchasable( product );
			
			((AIROUYAIAPANEExtensionContext)context).ouyaFacade.requestPurchase( productToBuy, ((AIROUYAIAPANEExtensionContext)context).purchaseListener );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
			
			((AIROUYAIAPANEExtensionContext)context).notifyAIR( "PURCHASE_FAILURE", "Error making Purchase," + exception.getMessage() );
		}
		
		return null;
	}

}
