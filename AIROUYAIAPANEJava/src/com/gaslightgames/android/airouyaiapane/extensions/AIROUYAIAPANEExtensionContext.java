package com.gaslightgames.android.airouyaiapane.extensions;

import java.util.HashMap;
import java.util.Map;

import tv.ouya.console.api.OuyaFacade;

import com.adobe.fre.FREContext;
import com.adobe.fre.FREFunction;

public class AIROUYAIAPANEExtensionContext extends FREContext
{
	public OuyaFacade ouyaFacade;
	public AIROUYAIAPANEProductListListener productListListener;
	public AIROUYAIAPANEPurchaseListener	purchaseListener;
	public AIROUYAIAPANEReceiptListener		receiptListener;
	public AIROUYAIAPANEGamerUUIDListener	gamerUUIDListener;
	
	public void notifyTest()
	{
		// Create an Event Name
		String eventName = "TEST_IAP";
		
		dispatchStatusEventAsync( eventName, "Testing IAP." );
	}
	
	/**
	 * Sends the eventName and data strings up to AIR.
	 * <p>
	 * Allowable EventNames:
	 * <br>INIT_FAILURE
	 * <br>PRODUCT_SUCCESS
	 * <br>PRODUCT_CANCEL
	 * <br>PRODUCT_FAILURE
	 * <br>PURCHASE_SUCCESS
	 * <br>PURCHASE_CANCEL
	 * <br>PURCHASE_FAILURE
	 * <br>RECEIPT_SUCCESS
	 * <br>RECEIPT_CANCEL
	 * <br>RECEIPT_FAILURE
	 * <br>GAMMERUUID_SUCCESS
	 * <br>GAMMERUUID_CANCEL
	 * <br>GAMMERUUID_FAILURE
	 * 
	 * @param eventName The EventName String
	 * @param data		Comma separated data String
	 */
	public void notifyAIR( String eventName, String data )
	{
		dispatchStatusEventAsync( eventName, data );
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
		functionMap.put( "isOUYAIAPSupported", new AIROUYAIAPANESupported() );
		functionMap.put( "setTestMode", new AIROUYAIAPANESetTestMode() );
		functionMap.put( "getProdInfo", new AIROUYAIAPANEGetProdInfo() );
		functionMap.put( "makeProdPurchase", new AIROUYAIAPANEMakeProdPurchase() );
		functionMap.put( "getProdReceipt", new AIROUYAIAPANEGetProdReceipt() );
		functionMap.put( "getGamerUUID", new AIROUYAIAPANEGetGamerUUID() );

		return functionMap;
	}

}
