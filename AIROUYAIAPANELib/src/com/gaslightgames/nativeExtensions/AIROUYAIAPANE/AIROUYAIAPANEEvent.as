package com.gaslightgames.nativeExtensions.AIROUYAIAPANE
{
	import flash.events.Event;
	
	public class AIROUYAIAPANEEvent extends Event
	{		
		public static const PRODUCT:String					= "product";
		
		public static const SUCCESS:String 					= "SUCCESS";
		public static const FAILURE:String 					= "FAILURE";
		public static const CANCEL:String  					= "CANCEL";
		
		private var _status:String;
		
		private var _product:Product;
		
		public function AIROUYAIAPANEEvent( type:String, status:String = "", product:Product = null, bubbles:Boolean = false, cancelable:Boolean = false )
		{
			_status = status;
			_product = product;
			
			super( type, bubbles, cancelable );
		}

		public function get status():String
		{
			return _status;
		}
		
		public function get product():Product
		{
			return _product;
		}
	}
}