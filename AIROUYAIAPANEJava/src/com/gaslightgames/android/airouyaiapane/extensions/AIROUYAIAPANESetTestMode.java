package com.gaslightgames.android.airouyaiapane.extensions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AIROUYAIAPANESetTestMode implements FREFunction
{

	@Override
	public FREObject call( FREContext context, FREObject[] passedArgs )
	{
		try
		{
			((AIROUYAIAPANEExtensionContext)context).ouyaFacade.setTestMode();
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		
		return null;
	}

}
