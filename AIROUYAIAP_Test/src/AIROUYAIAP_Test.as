package
{
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.AIROUYAIAPANE;
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.AIROUYAIAPANEEvent;
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.Gamer;
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.Product;
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.Purchase;
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.Receipt;
	
	import flash.display.Sprite;
	import flash.events.Event;
	import flash.net.URLLoader;
	import flash.net.URLLoaderDataFormat;
	import flash.net.URLRequest;
	import flash.utils.ByteArray;
	import flash.utils.Endian;
	
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
			var urlRequest:URLRequest = new URLRequest( "key.der" );	// Needs to be in your bin directory!
			var urlLoader:URLLoader = new URLLoader();
			urlLoader.addEventListener( Event.COMPLETE, onKeyLoad );
			urlLoader.dataFormat = URLLoaderDataFormat.BINARY;
			urlLoader.load( urlRequest );
		}
		
		private function onKeyLoad( event:Event ):void
		{
			( event.target as URLLoader ).removeEventListener( Event.COMPLETE, onKeyLoad );
			
			// Get the Key data - as a ByteArray so we can pass it to the ANE
			var key:ByteArray = ( event.target as URLLoader ).data as ByteArray;
			key.endian = Endian.LITTLE_ENDIAN;
			
			// Simple way to read the values and make sure your key matches.
			this.checkKey( key );
			
			this.ouyaIap = AIROUYAIAPANE.getInstance( "YOUR_OUYA_DEVELOPER_UUID", key );
			this.ouyaIap.addEventListener( AIROUYAIAPANEEvent.PRODUCT, onProduct );
			this.ouyaIap.addEventListener( AIROUYAIAPANEEvent.PURCHASE, onPurchase );
			this.ouyaIap.addEventListener( AIROUYAIAPANEEvent.RECEIPT, onReceipt );
			this.ouyaIap.addEventListener( AIROUYAIAPANEEvent.GAMER, onGamer );
			this.ouyaIap.getProductInfo( "test" );									// You will need a product on OUYAs server!
			//this.ouyaIap.getProductReceipts();									// Not yet updated to new ODK
			this.ouyaIap.getGamerUUID();
		}
		
		private function onProduct( iapEvent:AIROUYAIAPANEEvent ):void
		{
			var product:Product = iapEvent.data as Product;
			if( null != product )
			{
				trace( "Product Received: " + product.identifier + ", " + product.name + ", " + product.price );
				
				this.ouyaIap.makeProductPurchase( product );
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
		
		private function checkKey( key:ByteArray ):void
		{
			key.position = 0;
			var keyStr:String = "";
			while( key.bytesAvailable )
			{
				var byte:uint = key.readUnsignedByte();
				keyStr += byte.toString(16).substr(-2);
			}
			trace( "Key: " + keyStr );
		}
	}
}