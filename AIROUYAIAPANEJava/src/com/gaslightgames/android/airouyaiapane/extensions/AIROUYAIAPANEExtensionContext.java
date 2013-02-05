package com.gaslightgames.android.airouyaiapane.extensions;

import java.util.HashMap;
import java.util.Map;

import tv.ouya.console.api.OuyaFacade;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

public class AIROUYAIAPANEExtensionContext extends FREContext
{
	public OuyaFacade ouyaFacade;
	
	public void notifyTest()
	{
		// Create an Event Name
		String eventName = "TEST_IAP";
		
		dispatchStatusEventAsync( eventName, "Testing IAP." );
	}
	
	@Override
	public void dispose()
	{
		
	}

	@Override
	public Map<String, FREFunction> getFunctions()
	{
		Map<String, FREFunction> functionMap = new HashMap<String, FREFunction>();

		functionMap.put( "initIAP", new AIROUYAIAPANEInit() );
		functionMap.put( "testIAP", new AIROUYAIAPANETest() );

		return functionMap;
	}

}
