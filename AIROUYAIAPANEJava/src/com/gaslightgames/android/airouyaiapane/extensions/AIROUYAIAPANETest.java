package com.gaslightgames.android.airouyaiapane.extensions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AIROUYAIAPANETest implements FREFunction
{

	@Override
	public FREObject call( FREContext context, FREObject[] args )
	{
		((AIROUYAIAPANEExtensionContext)context).notifyTest();
		
		return null;
	}

}
