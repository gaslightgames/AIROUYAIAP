package com.gaslightgames.android.airouyaiapane.extensions;

import java.util.List;

import org.json.JSONObject;

import com.adobe.fre.FREContext;

import android.os.Bundle;
import tv.ouya.console.api.OuyaEncryptionHelper;
import tv.ouya.console.api.OuyaResponseListener;
import tv.ouya.console.api.Receipt;

public class AIROUYAIAPANEReceiptListener implements OuyaResponseListener<String>
{
	private FREContext context;
	
	public AIROUYAIAPANEReceiptListener( FREContext context )
	{
		this.context = context;
	}
	
	@Override
	public void onCancel()
	{
		((AIROUYAIAPANEExtensionContext)context).notifyAIR( "RECEIPT_CANCEL", "receipt cancelled." );
	}

	@Override
	public void onFailure( int errorCode, String errorMessage, Bundle bundle )
	{
		((AIROUYAIAPANEExtensionContext)context).notifyAIR( "RECEIPT_FAILURE", errorMessage );
	}

	@Override
	public void onSuccess( String receiptResponse )
	{
		OuyaEncryptionHelper helper = new OuyaEncryptionHelper();
		List<Receipt> receipts = null;
		
		try
		{
			JSONObject jsonResponse = new JSONObject( receiptResponse );
			
			if( jsonResponse.has( "key" ) && jsonResponse.has( "iv" ) )
			{
				receipts = helper.decryptReceiptResponse( jsonResponse, ((AIROUYAIAPANEExtensionContext)context).publicKey );
			}
			else
			{
				receipts = helper.parseJSONReceiptResponse( receiptResponse );
			}
		}
		catch( Exception exception )
		{
			((AIROUYAIAPANEExtensionContext)context).notifyAIR( "RECEIPT_FAILURE", exception.getMessage() );
			
			throw new RuntimeException( exception );
		}
		
		for( Receipt r : receipts )
		{
			String receipt = r.getIdentifier() + "," + r.getPriceInCents() + "," + r.getGeneratedDate() + "," + r.getPurchaseDate();
			
			((AIROUYAIAPANEExtensionContext)context).notifyAIR( "RECEIPT_SUCCESS", receipt );
		}
	}
}
