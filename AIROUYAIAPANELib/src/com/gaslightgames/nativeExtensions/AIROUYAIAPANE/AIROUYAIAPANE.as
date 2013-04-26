package com.gaslightgames.nativeExtensions.AIROUYAIAPANE
{
	import flash.events.EventDispatcher;
	import flash.events.IEventDispatcher;
	import flash.events.StatusEvent;
	import flash.external.ExtensionContext;
	import flash.utils.ByteArray;
	
	public class AIROUYAIAPANE extends EventDispatcher
	{
		public static var instance:AIROUYAIAPANE;
		
		private static var extContext:ExtensionContext = null;
		
		private static var _isSupported:Boolean = false;
		private static var _isSupportedSet:Boolean = false;
		private static var _developerId:String;
		
		/**
		 * On the first call - make sure to pass BOTH developerId AND applicationKey.
		 * <BR>On subsequent calls, you don't need the Application Key.
		 * <BR>The testing Boolean affects the new change in ODK 1.0.3 and allows testing of purchases.
		 */
		public static function getInstance( developerId:String, applicationKey:ByteArray = null, testing:Boolean = true ):AIROUYAIAPANE
		{
			if( null == instance )
			{
				if( null != applicationKey )
				{
					_developerId = developerId;
					instance = new AIROUYAIAPANE( developerId, applicationKey, testing, new SingletonEnforcer() );
				}
				else
				{
					throw new Error( "Application Key Required on First Call!" );
				}
			}
			
			if( _developerId == developerId )
			{
				return instance;
			}
			else
			{
				throw new Error( "Developer ID does not match." );
			}
		}
		
		public function AIROUYAIAPANE( developerId:String, applicationKey:ByteArray, testing:Boolean, enforcer:SingletonEnforcer, target:IEventDispatcher = null )
		{
			trace( "Building OUYA IAP ANE" );
			if( !extContext )
			{
				trace( "Building Extension Context" );
				extContext = ExtensionContext.createExtensionContext( "com.gaslightgames.AIROUYAIAPANE", "AIROUYAIAPANE" );
				
				extContext.call( "initIAP", developerId, applicationKey, testing );
				
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
			var codeSplitStrings:Array = String( statusEvent.code ).split( "_" );
			var levelSplitStrings:Array = String( statusEvent.level ).split( "," );
			
			if( "PRODUCT" == codeSplitStrings[0] )
			{
				this.processProduct( codeSplitStrings, levelSplitStrings );
			}
			if( "PURCHASE" == codeSplitStrings[0] )
			{
				this.processPurchase( codeSplitStrings, levelSplitStrings );
			}
			if( "RECEIPT" == codeSplitStrings[0] )
			{
				this.processReceipt( codeSplitStrings, levelSplitStrings );
			}
			if( "GAMERUUID" == codeSplitStrings[0] )
			{
				this.processGamer( codeSplitStrings, levelSplitStrings );
			}
		}
		
		private function processProduct( code:Array, level:Array ):void
		{
			var product:Product = null;
			
			if( "SUCCESS" == code[1] )
			{
				// Build product with complete information
				product = new Product( level[0], level[1], Number( level[2] ) );
			}
			
			// Dispatch Product Event
			this.dispatchEvent( new AIROUYAIAPANEEvent( AIROUYAIAPANEEvent.PRODUCT, product, code[1]) );
		}
		
		private function processPurchase( code:Array, level:Array ):void
		{
			var purchase:Purchase = null;
			
			if( "SUCCESS" == code[1] )
			{
				// Build purchase with complete information
				purchase = new Purchase( level[0], level[2], Number( level[1] ) );
			}
			
			// Dispatch Purchase Event
			this.dispatchEvent( new AIROUYAIAPANEEvent( AIROUYAIAPANEEvent.PURCHASE, purchase, code[1]) );
		}
		
		private function processReceipt( code:Array, level:Array ):void
		{
			var receipt:Receipt = null;
			
			if( "SUCCESS" == code[1] )
			{
				// Build Receipt with complete information
				receipt = new Receipt( level[0], Number( level[1] ), level[2], level[3] );
			}
			
			// Dispatch Receipt Event
			this.dispatchEvent( new AIROUYAIAPANEEvent( AIROUYAIAPANEEvent.RECEIPT, receipt, code[1]) );
		}
		
		private function processGamer( code:Array, level:Array ):void
		{
			var gamer:Gamer = null;
			
			if( "SUCCESS" == code[1] )
			{
				// Build Gamer with complete information
				gamer = new Gamer( level[0] );
			}
			
			// Dispatch Gamer Event
			this.dispatchEvent( new AIROUYAIAPANEEvent( AIROUYAIAPANEEvent.GAMER, gamer, code[1]) );
		}
		
		public function getProductInfo( product:String ):void
		{
			extContext.call( "getProdInfo", product );
		}
		
		public function makeProductPurchase( product:Product ):void
		{
			extContext.call( "makeProdPurchase", product.identifier, product.name, product.price );
		}
		
		public function getProductReceipts():void
		{
			extContext.call( "getProdReceipt" );
		}
		
		public function getGamerUUID():void
		{
			extContext.call( "getGamerUUID" );
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

internal class SingletonEnforcer{}