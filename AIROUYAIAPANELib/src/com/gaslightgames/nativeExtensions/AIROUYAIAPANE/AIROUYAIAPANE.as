package com.gaslightgames.nativeExtensions.AIROUYAIAPANE
{
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	
	public class AIROUYAIAPANE extends EventDispatcher
	{
		private static var extContext:ExtensionContext = null;
		
		public function AIROUYAIAPANE( target:IEventDispatcher=null )
		{
			trace( "Building OUYA IAP ANE" );
			if( !extContext )
			{
				trace( "Building Extension Context" );
				extContext = ExtensionContext.createExtensionContext( "com.gaslightgames.AIROUYAIAPANE", "AIROUYAIAPANE" );
				
				extContext.call( "initIAP" );
				
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
			
			super(target);
		}
		
		private function onStatus( statusEvent:StatusEvent ):void
		{
			trace( "Received Event from Native Code." );
			trace( statusEvent.level );
		}
		
		public function test():void
		{
			extContext.call( "testIAP" );
		}
	}
}