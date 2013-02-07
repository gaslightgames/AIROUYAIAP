package com.gaslightgames.nativeExtensions.AIROUYAIAPANE
{
	public class Receipt
	{
		private var _identifier:String;
		private var _price:Number;
		private var _genDate:String;
		private var _purDate:String
		
		public function Receipt( identifier:String, price:Number, genDate:String, purDate:String )
		{
			this._identifier = identifier;
			this._price = price;
			this._genDate = genDate;
			this._purDate = purDate;
		}
		
		public function get identifier():String
		{
			return this._identifier;
		}
		
		public function get price():Number
		{
			return this._price;
		}
		
		public function get generatedDate():String
		{
			return this._genDate;
		}
		
		public function get purchasedDate():String
		{
			return this._purDate;
		}
	}
}