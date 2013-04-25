package com.gaslightgames.nativeExtensions.AIROUYAIAPANE
{
	import flash.events.IEventDispatcher;
	import flash.utils.ByteArray;

	public class AIROUYAIAPANE
	{
		public function AIROUYAIAPANE( developerId:String, applicationKey:ByteArray, target:IEventDispatcher = null )
		{
			trace( "Not supported on this platform!" );
		}
		
		public function get isOUYAIAPSupported():Boolean
		{
			return false;
		}
	}
}