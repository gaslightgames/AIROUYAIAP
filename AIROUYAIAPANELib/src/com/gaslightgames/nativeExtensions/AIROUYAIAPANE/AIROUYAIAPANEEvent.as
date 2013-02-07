package com.gaslightgames.nativeExtensions.AIROUYAIAPANE
{
	import flash.events.Event;
	
	public class AIROUYAIAPANEEvent extends Event
	{		
		public static const PRODUCT:String					= "product";
		public static const RECEIPT:String					= "receipt";
		public static const PURCHASE:String					= "purchase";
		public static const GAMER:String					= "gamer";
		
		public static const SUCCESS:String 					= "SUCCESS";
		public static const FAILURE:String 					= "FAILURE";
		public static const CANCEL:String  					= "CANCEL";
		
		private var _status:String;
		
		private var _data:*;
		
		/**
		 * Create a new AIROUYAIAPANEEvent.
		 * <p>
		 * type:	Either PRODUCT, RECEIPT, PURCHASE or GAMER
		 * <br>data:	Either Product, Purchase, Receitp, Gamer or null
		 * <br>status:	Either SUCCESS, FAILURE or CANCEL
		 */
		public function AIROUYAIAPANEEvent( type:String, data:*, status:String = "", bubbles:Boolean = false, cancelable:Boolean = false )
		{
			_status = status;
			_data = data;
			
			super( type, bubbles, cancelable );
		}

		/**
		 * Status can be one of 3 types:
		 * <br> SUCCESS
		 * <br> FAILURE
		 * <br> CANCEL
		 */
		public function get status():String
		{
			return _status;
		}
		
		/**
		 * Data can be returned as one of several objects:
		 * <br> Product: 	returned after Product Info Request.
		 * <br> Receipt: 	returned after Receipt Request.
		 * <br> Purchase:	returned after Purchase Request.
		 * <br> Gamer:		returned after a Gamer UUID Request.
		 * <br> null:		returned after any FAILURE/CANCEL event status.
		 */
		public function get data():*
		{
			return _data;
		}
	}
}