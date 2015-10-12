package com.ftwinston.KillerMinecraft.Modules.FlyingIslands;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.World;

public class IslandGenerator extends org.bukkit.generator.ChunkGenerator
{
	static final int solidChance1x1 = 16, solidChance2x2 = 11, solidChance3x3 = 30;

	@SuppressWarnings("deprecation")
	final byte air = (byte)Material.AIR.getId();
	@SuppressWarnings("deprecation")
	final byte dirt = (byte)Material.DIRT.getId();
	
	final int blockLayers = 12;

	public static void setSeedForChunk(Random random, World world, int cx, int cz)
	{
        random.setSeed(world.getSeed());
        long xRand = random.nextLong() / 2L * 2L + 1L;
        long zRand = random.nextLong() / 2L * 2L + 1L;
        random.setSeed((long) cx * xRand + (long) cz * zRand ^ world.getSeed());
	}
	
	static final int chanceOfSeeding = 20; // percent
	public static IslandOutline getOutlineForChunk(Random random, World world, int cx, int cz)
	{
		setSeedForChunk(random, world, cx, cz);
		if ( random.nextInt(100) < chanceOfSeeding )
			return null;
		
		return new IslandOutline(random, cx, cz);
	}
	
	public byte[][] generateBlockSections(World world, Random random, int cx, int cz, BiomeGrid biomes)
	{
		boolean[] solidMask = new boolean[256];
		boolean any = false;
		
		Random tmp = new Random();
		for ( int x = cx - 2; x <= cx + 2; x ++ )
			for ( int z = cz - 2; z <= cz + 2; z ++ )
			{
				IslandOutline outline = getOutlineForChunk(tmp, world, x, z);
				if ( outline != null && outline.containsChunk(x, z) )
				{
					outline.applyDataTo(solidMask, cx, cz);
					any = true;
				}
			}
		
		if ( !any )
			return new byte[1][];
		
		byte[][] chunk = new byte[16][];

		
		for ( int i=0; i<16; i++ )
		{
			byte[] layer = new byte[4096];
			chunk[i] = layer;
			
			if ( i == 6 )
				for ( int times = 0; times<blockLayers; times++ )
					for ( int j=0; j<solidMask.length; j++ )
						layer[solidMask.length * times + j] = solidMask[j] ? dirt : air;
		}
		
		return chunk; 
	}
/*		
	private void setMaterialAt(byte[][] chunk, int x, int y, int z, Material material)
	{
		int sec_id = (y >> 4);
		int yy = y & 0xF;
		if (chunk[sec_id] == null)
			chunk[sec_id] = new byte[4096];

		chunk[sec_id][(yy << 8) | (z << 4) | x] = (byte) material.getId();
	}
*/
}