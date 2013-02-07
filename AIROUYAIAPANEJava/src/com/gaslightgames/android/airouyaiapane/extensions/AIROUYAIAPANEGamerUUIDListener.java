package com.gaslightgames.android.airouyaiapane.extensions;

import com.adobe.fre.FREContext;

import android.os.Bundle;
import tv.ouya.console.api.OuyaResponseListener;

public class AIROUYAIAPANEGamerUUIDListener implements OuyaResponseListener<String>
{
	private FREContext context;
	
	public AIROUYAIAPANEGamerUUIDListener( FREContext context )
	{
		this.context = context;
	}

	@Override
	public void onCancel()
	{
		((AIROUYAIAPANEExtensionContext)context).notifyAIR( "GAMERUUID_CANCEL", "Gamer UUID cancelled." );
	}

	@Override
	public void onFailure( int errorCode, String errorMessage, Bundle bundle )
	{
		((AIROUYAIAPANEExtensionContext)context).notifyAIR( "GAMERUUID_FAILURE", errorMessage );
	}

	@Override
	public void onSuccess( String result )
	{
		((AIROUYAIAPANEExtensionContext)context).notifyAIR( "GAMERUUID_SUCCESS", result );
	}

}
