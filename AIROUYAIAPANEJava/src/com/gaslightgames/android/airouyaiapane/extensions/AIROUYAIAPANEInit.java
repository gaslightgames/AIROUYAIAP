package com.gaslightgames.android.airouyaiapane.extensions;

import tv.ouya.console.api.OuyaFacade;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AIROUYAIAPANEInit implements FREFunction
{

	public FREObject call( FREContext context, FREObject[] passedArgs )
	{
		try
		{
			FREObject fro = passedArgs[0];
			String developerId = fro.getAsString();
			
			((AIROUYAIAPANEExtensionContext)context).ouyaFacade = OuyaFacade.getInstance();
			
			((AIROUYAIAPANEExtensionContext)context).productListListener = new AIROUYAIAPANEProductListListener( context );
			
			((AIROUYAIAPANEExtensionContext)context).ouyaFacade.init( context.getActivity(), developerId );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		
		return null;
	}

}
