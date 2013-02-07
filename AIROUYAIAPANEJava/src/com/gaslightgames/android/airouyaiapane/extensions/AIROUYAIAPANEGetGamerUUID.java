package com.gaslightgames.android.airouyaiapane.extensions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AIROUYAIAPANEGetGamerUUID implements FREFunction
{

	@Override
	public FREObject call( FREContext context, FREObject[] passedArgs )
	{
		try
		{
			((AIROUYAIAPANEExtensionContext)context).ouyaFacade.requestGamerUuid( ((AIROUYAIAPANEExtensionContext)context).gamerUUIDListener );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
			
			((AIROUYAIAPANEExtensionContext)context).notifyAIR( "GAMERUUID_FAILURE", "Error getting Gamer UUID," + exception.getMessage() );
		}
		
		return null;
	}

}
