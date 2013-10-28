package com.ftwinston.KillerMinecraft.Modules.FlyingIslands;

import java.util.Random;

public class IslandOutline
{
	static final int minIslandRadius = 6, maxIslandRadius = 48, islandRadiusDiff = maxIslandRadius - minIslandRadius + 1;
	
	public IslandOutline(Random random, int cx, int cz)
	{
		this.centerX = random.nextInt(16) + (cx << 4);
		this.centerZ = random.nextInt(16) + (cz << 4);
		this.maxRadius = random.nextInt(islandRadiusDiff) + minIslandRadius;
		dataSeed = random.nextLong();
		
		minX = centerX - maxRadius; maxX = centerX + maxRadius;
		minZ = centerZ - maxRadius; maxZ = centerZ + maxRadius;
	}
	public int centerX, centerZ, maxRadius;
	private int minX, maxX, minZ, maxZ;
	private long dataSeed;
	
	public boolean containsChunk(int cx, int cz)
	{
		int cMinX = cx << 4, cMaxX = cMinX + 16;
		int cMinZ = cz << 4, cMaxZ = cMinZ + 16;
		
		// first just see if it fits within the "square bounds". only if it does, consider the "circular bounds".
		if ( cMinX > maxX || cMaxX < minX || cMinZ > maxZ || cMaxZ < minZ )
			return false;
		 
		int circleDistX = Math.abs(centerX - cMinX + 8);
		int circleDistZ = Math.abs(centerZ - cMinZ + 8);
		
		int cornerDistSq = (circleDistX - 8) * (circleDistX - 8) + (circleDistZ - 8) * (circleDistZ - 8);
		return cornerDistSq <= maxRadius * maxRadius;
	}
	
	public void applyDataTo(boolean[] mask, int cx, int cz)
	{
		Random r = new Random(dataSeed);
		int xOffset = (cx << 4) - centerX, zOffset = (cz << 4) - centerZ;
		
		// TODO: need to change the noise generation so that the max value ALWAYS equals maxRadius
		double[] outlineMagnitudes = NoiseGenerator.generateScaledNoise(r, maxRadius, minIslandRadius);
		
		boolean[][] fullOutline = new boolean[maxRadius * 2 + 1][maxRadius * 2 + 1];
	}
}
