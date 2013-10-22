package com.ftwinston.KillerMinecraft.Modules.FlyingIslands;

import java.util.Random;

public enum Rotation
{
	NONE(),
	FLIP_HORIZONTAL() {
		@Override
		public void perform(byte[] data)
		{
			flipHorizontal(data);
		}
	},
	FLIP_VERTICAL() {
		@Override
		public void perform(byte[] data)
		{
			flipVertical(data);
		}
	},
	ROTATE_180() {
		@Override
		public void perform(byte[] data)
		{
			flipHorizontal(data);
			flipVertical(data);
		}
	},
	TRANSPOSE() {
		@Override
		public void perform(byte[] data)
		{
			transpose(data);
		}
	},
	TRANSPOSE_INVERSE() {
		@Override
		public void perform(byte[] data)
		{
			transpose(data);
			flipHorizontal(data);
			flipVertical(data);
		}
	},
	CLOCKWISE_90() {
		@Override
		public void perform(byte[] data)
		{
			transpose(data);
			flipHorizontal(data);
		}
	},
	ANTICLOCKWISE_90() {
		@Override
		public void perform(byte[] data)
		{
			transpose(data);
			flipVertical(data);
		}
	};
	
	public static final Rotation[] ALL = new Rotation[] { FLIP_HORIZONTAL, FLIP_VERTICAL, ROTATE_180, TRANSPOSE, TRANSPOSE_INVERSE, CLOCKWISE_90, ANTICLOCKWISE_90 };
	
	public void perform(byte[] data) { }
	
	private static void transpose(byte[] data)
	{
		for (int i = 0; i < 16; i++) 
			for (int j = i; j < 16; j++)
			{ 
				int i1 = i + j*16, i2 = j + i*16;
				byte temp = data[i1]; 
				data[i1] = data[i2]; 
				data[i2] = temp;
			}
	}
	
	private static void flipVertical(byte[] data)
	{
		for (int i = 0; i < 16; i++) 
			for (int j = 0; j < 8; j++)
			{ 
				int i1 = i + j*16, i2 = i + (15-j)*16;
				byte temp = data[i1]; 
				data[i1] = data[i2]; 
				data[i2] = temp;
			}
	}
	
	private static void flipHorizontal(byte[] data)
	{
		for (int i = 0; i < 8; i++) 
			for (int j = 0; j < 16; j++)
			{ 
				int i1 = i + j*16, i2 = 15-i + j*16;
				byte temp = data[i1]; 
				data[i1] = data[i2]; 
				data[i2] = temp;
			}
	}
	
	public static void performOne(byte[] data, Random r, boolean considerNone, Rotation... possibilities)
	{
		int index = r.nextInt(considerNone ? possibilities.length + 1 : possibilities.length);
		if ( index == possibilities.length )
			return;
		
		possibilities[index].perform(data);
	}
}