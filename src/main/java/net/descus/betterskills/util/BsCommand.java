package net.descus.betterskills.util;

import com.google.common.collect.Lists;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import java.util.List;

public class BsCommand extends CommandBase {

    private static final List<String> ALIASES = Lists.newArrayList("bs", "betterskills", "better_skills");


    @Override
    public String getName() {
        return "betterskills";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "betterskills <arg>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        if(sender instanceof EntityPlayer) {
            if (checkPermission(server, sender)) {
                switch (args[0]){
                    case "edit":
                        if(args[1].equalsIgnoreCase("true")){

                        }else if(args[1].equalsIgnoreCase("false")){

                        } else {
                            sender.sendMessage(new TextComponentString(TextFormatting.RED + getUsage(sender)));
                        }
                        break;
                }
            }
        }

    }
}
