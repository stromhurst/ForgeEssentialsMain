package com.ForgeEssentials.commands;

import net.minecraft.src.DedicatedServer;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ICommandSender;

import com.ForgeEssentials.core.commands.ForgeEssentialsCommandBase;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.network.PacketDispatcher;

public class CommandServerDo extends ForgeEssentialsCommandBase
{

	@Override
	public String getCommandName()
	{
		return "serverdo";
	}

	@Override
	public void processCommandPlayer(EntityPlayer player, String[] args)
	{
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER && args.length >= 1)
		{
			String cmd = args[0];
			for (int i = 1; i < args.length; ++i)
			{
				cmd = cmd + " " + args[i]; 
			}
			String result = DedicatedServer.getServer().executeCommand(cmd);
			player.sendChatToPlayer(result);
		}
	}

	@Override
	public String getSyntaxPlayer(EntityPlayer player)
	{
		return "/serverdo <command> [command args]";
	}

	@Override
	public String getInfoPlayer(EntityPlayer player)
	{
		return "Runs command with any args as though it had been typed from the server console.";
	}

	@Override
	public boolean canConsoleUseCommand()
	{
		return false;
	}
	
	/**
	 * Restricts the usage of this command to ops so random jerkbags can't op themselves.
	 * Once our permissions system gets working, we can use canPlayerUseCommand instead.
	 */
	public int getRequiredPermissionLevel()
	{
		return 3;
	}

	@Override
	public boolean canPlayerUseCommand(EntityPlayer player)
	{
		return true;
	}

	@Override
	public void processCommandConsole(ICommandSender sender, String[] args)
	{
		// Does nothing on the console.
	}

	@Override
	public String getSyntaxConsole()
	{
		// Not meant to be run on the console.
		return null;
	}

	@Override
	public String getInfoConsole()
	{
		// Not meant to be run on the console.
		return null;
	}
}
