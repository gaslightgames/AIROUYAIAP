package
{
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.AIROUYAIAPANE;
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.AIROUYAIAPANEEvent;
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.Gamer;
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.Product;
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.Purchase;
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.Receipt;
	
	import flash.display.Sprite;
	
	[SWF(backgroundColor="#FF0000", frameRate="60", width="1920", height="1080")]
	public class AIROUYAIAP_Test extends Sprite
	{
		private var ouyaIap:AIROUYAIAPANE;
		
		public function AIROUYAIAP_Test()
		{
			super();
			
			this.init();
		}
		
		private function init():void
		{
			this.ouyaIap = new AIROUYAIAPANE( "YOUR_OUYA_DEVELOPER_UUID" );
			this.ouyaIap.addEventListener( AIROUYAIAPANEEvent.PRODUCT, onProduct );
			this.ouyaIap.addEventListener( AIROUYAIAPANEEvent.PURCHASE, onPurchase );
			this.ouyaIap.addEventListener( AIROUYAIAPANEEvent.RECEIPT, onReceipt );
			this.ouyaIap.addEventListener( AIROUYAIAPANEEvent.GAMER, onGamer );
			this.ouyaIap.setTestMode();
			this.ouyaIap.getProductInfo( "test" );
			this.ouyaIap.makeProductPurchase( "test" );
			this.ouyaIap.getProductReceipts();
			this.ouyaIap.getGamerUUID();
		}
		
		private function onProduct( iapEvent:AIROUYAIAPANEEvent ):void
		{
			var product:Product = iapEvent.data as Product;
			if( null != product )
			{
				trace( "Product Received: " + product.identifier + ", " + product.name + ", " + product.price );
			}
		}
		
		private function onPurchase( iapEvent:AIROUYAIAPANEEvent ):void
		{
			var purchase:Purchase = iapEvent.data as Purchase;
			if( null != purchase )
			{
				trace( "Purchase Made: " + purchase.identifier + ", " + purchase.name + ", " + purchase.price );
			}
		}
		
		private function onReceipt( iapEvent:AIROUYAIAPANEEvent ):void
		{
			trace( "Receipt Received: " + iapEvent.status );
			
			var receipt:Receipt = iapEvent.data as Receipt;
			if( null != receipt )
			{
				trace( "Receipt Received: " + receipt.identifier + ", " + receipt.price + ", " + receipt.generatedDate + ", " + receipt.purchasedDate );
			}
		}
		
		private function onGamer( iapEvent:AIROUYAIAPANEEvent ):void
		{
			var gamer:Gamer = iapEvent.data as Gamer;
			if( null != gamer )
			{
				trace( "Gamer UUID Received: " + gamer.udid );
			}
		}
	}
}