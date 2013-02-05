package com.gaslightgames.android.airouyaiapane.extensions;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREExtension;

public class AIROUYAIAPANEExtension implements FREExtension
{
	public static FREContext extensionContext;
	
	public FREContext createContext( String contextType )
	{
		return new AIROUYAIAPANEExtensionContext();
	}

	public void dispose()
	{
		
	}

	public void initialize()
	{
		
	}

}
