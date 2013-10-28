package com.ftwinston.KillerMinecraft.Modules.FlyingIslands;

import org.bukkit.World.Environment;

import com.ftwinston.KillerMinecraft.Option;
import com.ftwinston.KillerMinecraft.WorldConfig;
import com.ftwinston.KillerMinecraft.WorldGenerator;

public class FlyingIslands extends WorldGenerator
{
	@Override
	public Option[] setupOptions()
	{
		return new Option[] { };
	}
	
	@Override
	public void setupWorld(WorldConfig world, Runnable runWhenDone)
	{
		if ( world.getEnvironment() == Environment.NORMAL )
			world.setGenerator(new IslandGenerator());
			
		createWorld(world, runWhenDone);
	}
}
