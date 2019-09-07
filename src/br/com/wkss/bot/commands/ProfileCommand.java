package br.com.wkss.bot.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProfileCommand implements Command {

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    public static String line = "\n";

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (args[0].equalsIgnoreCase(".profile")) {
                EmbedBuilder builder = new EmbedBuilder();
                builder.setColor(Color.CYAN);
                builder.setAuthor(event.getAuthor().getName(), event.getAuthor().getAvatarUrl(), event.getAuthor().getAvatarUrl());
                builder.setThumbnail(event.getAuthor().getAvatarUrl());
                builder.setTitle("Informations:");
                builder.setDescription("**ID:** " + event.getMember().getUser().getId() + line);
                builder.appendDescription("**Date of join:** " + event.getMember().getJoinDate().format(DateTimeFormatter.ofPattern("hh/MM/yyyy " + "hh:mm a")) + line + line);
                if (event.getMember().getOnlineStatus() == OnlineStatus.ONLINE) {
                    builder.appendDescription("**Status:** Online" + line);
                } else if (event.getMember().getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB) {
                    builder.appendDescription("**Status:** Busy." + line);
                } else if (event.getMember().getOnlineStatus() == OnlineStatus.IDLE) {
                    builder.appendDescription("**Status:** Absent." + line);
                } else if (event.getMember().getOnlineStatus() == OnlineStatus.INVISIBLE || event.getMember().getOnlineStatus() == OnlineStatus.OFFLINE) {
                    builder.appendDescription("**Status:** Invisible." + line);
                } else if (event.getMember().getOnlineStatus() == OnlineStatus.UNKNOWN) {
                    builder.appendDescription("**Status:** Unknow." + line);
                }
                if (event.getMember().getNickname() == null) {
                    builder.appendDescription("**Nickname:** " + event.getAuthor().getName() + " does not have any nickname." + line);
                } else {
                    builder.appendDescription("**Nickname:** " + event.getMember().getNickname() + line);
                }
                builder.appendDescription("**Quantity of roles:** " + event.getMember().getRoles().size() + line);
                if (event.getMember().getOnlineStatus() == OnlineStatus.INVISIBLE || event.getMember().getOnlineStatus() == OnlineStatus.OFFLINE) {
                    builder.appendDescription("**Activities:** The user is not doing any activity." + line + line);
                } else if (event.getMember().getGame() == null) {
                    builder.appendDescription("**Activities:** The user is not doing any activity." + line + line);
                } else {
                    builder.appendDescription("**Activities:** " + event.getMember().getGame() + "." + line + line);
                }
                builder.appendDescription("**Roles:** ");
                for (int i = 0; i < event.getMember().getRoles().size(); i++) {
                    builder.appendDescription("`" + event.getMember().getRoles().get(i).getName() + "`" + "\n");
                }
                builder.setFooter("Order by: " + event.getAuthor().getName() + " • " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("EEEE" + " hh:mm a ")), event.getMember().getUser().getAvatarUrl());
                event.getChannel().sendMessage(builder.build()).queue();
        }
    }
    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        System.out.println("[INFOMATION] '!profile' Command was used! By: " + event.getAuthor().getName());
    }

    @Override
    public String help() {
        return null;
    }
}