package br.com.wkss.bot.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class MuteCommand extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(".mute")) {
            if(args.length <= 1) {
                sendErrorMessage(event.getChannel(), event.getMember());
            } else {
                Member target = event.getMessage().getMentionedMembers().get(0);
                Role role = event.getGuild().getRolesByName("Muted", true).get(0);

                event.getGuild().getController().addSingleRoleToMember(target, role).queue();

                if(args.length >= 3) {
                    String reason = "";
                    for(int i = 0; i < args.length; i++) {
                        reason += args[i] + " ";
                    }
                }
            }
        }
    }
    @SuppressWarnings("Duplicate")
    public void sendErrorMessage(TextChannel textChannel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid usage!");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.decode("#ff0000"));
        builder.addField("Use: /mute <@nickname> <reason>", "", false);
        textChannel.sendMessage(builder.build()).complete().delete().queueAfter(15, TimeUnit.SECONDS);
    }
}
