package com.gaslightgames.android.airouyaiapane.extensions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AIROUYAIAPANEGetProdReceipt implements FREFunction
{

	@Override
	public FREObject call( FREContext context, FREObject[] passedArgs )
	{
		try
		{
			((AIROUYAIAPANEExtensionContext)context).ouyaFacade.requestReceipts( ((AIROUYAIAPANEExtensionContext)context).receiptListener );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
			
			((AIROUYAIAPANEExtensionContext)context).notifyAIR( "RECEIPT_FAILURE", "Error getting receipts," + exception.getMessage() );
		}
		
		return null;
	}

}
