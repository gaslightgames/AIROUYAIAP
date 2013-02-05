package
{
	import com.gaslightgames.nativeExtensions.AIROUYAIAPANE.AIROUYAIAPANE;
	
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
			this.ouyaIap = new AIROUYAIAPANE();
			
			this.ouyaIap.test();
		}
	}
}