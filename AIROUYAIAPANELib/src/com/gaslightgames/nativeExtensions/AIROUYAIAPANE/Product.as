package com.gaslightgames.nativeExtensions.AIROUYAIAPANE
{
	public class Product
	{
		private var _identifier:String;
		private var _name:String;
		private var _price:Number;
		
		public function Product( identifier:String, name:String, price:Number )
		{
			this._identifier = identifier;
			this._name = name;
			this._price = price;
		}
		
		public function get identifier():String
		{
			return this._identifier;
		}
		
		public function get name():String
		{
			return this._name;
		}
		
		public function get price():Number
		{
			return this._price;
		}
	}
}