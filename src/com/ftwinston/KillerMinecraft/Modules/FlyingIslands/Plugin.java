package com.ftwinston.KillerMinecraft.Modules.FlyingIslands;

import org.bukkit.Material;
import org.bukkit.World.Environment;

import com.ftwinston.KillerMinecraft.WorldGenerator;
import com.ftwinston.KillerMinecraft.WorldGeneratorPlugin;

public class Plugin extends WorldGeneratorPlugin
{
	@Override
	public Environment getWorldType() { return Environment.NORMAL; }
	
	@Override
	public String[] getDescriptionText() { return new String[] {"Play on islands flying in an infinite sky"}; }
	
	@Override
	public Material getMenuIcon() { return Material.FEATHER; }
	
	@Override
	public WorldGenerator createInstance()
	{
		return new FlyingIslands();
	}
}