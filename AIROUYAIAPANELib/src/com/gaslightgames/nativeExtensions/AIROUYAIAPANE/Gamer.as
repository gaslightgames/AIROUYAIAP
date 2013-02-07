package com.gaslightgames.nativeExtensions.AIROUYAIAPANE
{
	public class Gamer
	{
		private var _udid:String
		
		public function Gamer( udid:String )
		{
			this._udid = udid;
		}
		
		public function get udid():String
		{
			return this._udid;
		}
	}
}