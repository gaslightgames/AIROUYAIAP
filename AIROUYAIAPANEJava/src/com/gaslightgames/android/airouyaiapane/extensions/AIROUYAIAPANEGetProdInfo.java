package com.gaslightgames.android.airouyaiapane.extensions;

import java.util.Arrays;
import java.util.List;

import tv.ouya.console.api.Purchasable;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;
import com.adobe.fre.FREObject;

public class AIROUYAIAPANEGetProdInfo implements FREFunction
{

	@Override
	public FREObject call( FREContext context, FREObject[] passedArgs )
	{
		try
		{
			FREObject fro = passedArgs[0];
			String product = fro.getAsString();
			
			List<Purchasable> productList = Arrays.asList( new Purchasable( product ) );
			
			((AIROUYAIAPANEExtensionContext)context).ouyaFacade.requestProductList( productList, ((AIROUYAIAPANEExtensionContext)context).productListListener );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		
		return null;
	}

}
