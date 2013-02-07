package com.gaslightgames.android.airouyaiapane.extensions;

import java.io.IOException;
import java.util.List;

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
			//receipts = helper.decryptReceiptResponse( receiptResponse, null );
			receipts = helper.parseJSONReceiptResponse( receiptResponse );
			
			if( receipts.isEmpty() )
			{
				((AIROUYAIAPANEExtensionContext)context).notifyAIR( "RECEIPT_SUCCESS", "" );
			}
			else
			{
				for( Receipt r : receipts )
				{
					String receipt = r.getIdentifier() + "," + r.getPriceInCents() + "," + r.getGeneratedDate() + "," + r.getPurchaseDate();
					
					((AIROUYAIAPANEExtensionContext)context).notifyAIR( "RECEIPT_SUCCESS", receipt );
				}
			}
		}
		catch( IOException ioException )
		{
			((AIROUYAIAPANEExtensionContext)context).notifyAIR( "RECEIPT_FAILURE", ioException.getMessage() );
			
			throw new RuntimeException( ioException );
		}
		/*catch ( GeneralSecurityException generalSecurityException )
		{
			generalSecurityException.printStackTrace();
			
			((AIROUYAIAPANEExtensionContext)context).notifyAIR( "RECEIPT_FAILURE", generalSecurityException.getMessage() );
		}*/
	}

}
