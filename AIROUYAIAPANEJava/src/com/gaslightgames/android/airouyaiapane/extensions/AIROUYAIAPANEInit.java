package com.gaslightgames.android.airouyaiapane.extensions;

import tv.ouya.console.api.OuyaFacade;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AIROUYAIAPANEInit implements FREFunction
{

	public FREObject call( FREContext context, FREObject[] passedArgs )
	{
		((AIROUYAIAPANEExtensionContext)context).ouyaFacade = OuyaFacade.getInstance();
		
		return null;
	}

}
