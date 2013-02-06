package
{
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.AIROUYAIAPANE;
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.AIROUYAIAPANEEvent;
	
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
			this.ouyaIap = new AIROUYAIAPANE( "f1c9d6d9-59f3-441d-9612-2f84fe9e9760" );
			this.ouyaIap.addEventListener( AIROUYAIAPANEEvent.PRODUCT, onProduct );
			this.ouyaIap.getProductInfo( "test" );
		}
		
		private function onProduct( iapEvent:AIROUYAIAPANEEvent ):void
		{
			trace( "Product Received: " + iapEvent.product.name + ", Price: " + iapEvent.product.price );
		}
	}
}