package com.gaslightgames.nativeExtensions.AIROUYAIAPANE
{
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	public class AIROUYAIAPANE extends EventDispatcher
	{
		private static var extContext:ExtensionContext = null;
		
		private static var _isSupported:Boolean = false;
		private static var _isSupportedSet:Boolean = false;
		
		public function AIROUYAIAPANE( developerId:String, target:IEventDispatcher = null )
		{
			trace( "Building OUYA IAP ANE" );
			if( !extContext )
			{
				trace( "Building Extension Context" );
				extContext = ExtensionContext.createExtensionContext( "com.gaslightgames.AIROUYAIAPANE", "AIROUYAIAPANE" );
				
				extContext.call( "initIAP", developerId );
				
				if( extContext )
				{
					trace( "OUYA IAP ANE Initialised." );
					
					extContext.addEventListener( StatusEvent.STATUS, onStatus );
				}
				else
				{
					trace( "OUYA IAP ANE Initiasation FAILED!" );
				}
			}
			
			super( target );
		}
		
		private function onStatus( statusEvent:StatusEvent ):void
		{
			//trace( statusEvent.code, statusEvent.level );
			
			var codeSplitStrings:Array = String( statusEvent.code ).split( "_" );
			var levelSplitStrings:Array = String( statusEvent.level ).split( "," );
			
			if( "PRODUCT" == codeSplitStrings[0] )
			{
				// Build temp Product
				var product:Product = new Product( levelSplitStrings[0], levelSplitStrings[1], Number( levelSplitStrings[2] ) );
				
				// Call Dispatch Product
				this.dispatchProduct( codeSplitStrings[1], product );
			}
		}
		
		private function dispatchProduct( status:String, product:Product ):void
		{
			this.dispatchEvent( new AIROUYAIAPANEEvent( AIROUYAIAPANEEvent.PRODUCT, status, product ) );
		}
		
		public function setTestMode():void
		{
			extContext.call( "setTestMode" );
		}
		
		public function getProductInfo( product:String ):void
		{
			extContext.call( "getProdInfo", product );
		}
		
		public function test():void
		{
			extContext.call( "testIAP" );
		}
		
		public static function get isSupported():Boolean
		{
			if( !_isSupportedSet )
			{
				try
				{
					_isSupportedSet = true;
					
					var testContext:ExtensionContext = ExtensionContext.createExtensionContext( "com.gaslightgames.AIROUYAIAPANE", "AIROUYAIAPANE" );
					_isSupported = testContext.call( "isOUYAIAPSupported" );
					testContext.dispose();
				}
				catch( error:Error )
				{
					trace( error.message, error.errorID );
					
					return _isSupported;
				}
			}
			
			return _isSupported;
		}
	}
}