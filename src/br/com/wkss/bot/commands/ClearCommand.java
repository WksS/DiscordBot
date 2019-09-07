package br.com.wkss.bot.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ClearCommand extends ListenerAdapter {

        @Override
        public void onGuildMessageReceived (GuildMessageReceivedEvent event){
        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase(".clear")) {
            if (args.length <= 2) {
                sendErrorMessage(event.getChannel(), event.getMember());
            } else {
                event.getMessage().delete().queue();
                TextChannel target = event.getMessage().getMentionedChannels().get(0);
                purgeMessages(target, Integer.parseInt(args[2]));
            }
        }
    }
        private void sendErrorMessage (TextChannel channel, Member member){
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid usage!");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.decode("#EA2027"));
        builder.setDescription("Invalid arguments exception.");
        builder.addField("Proper usage: .clear <number of lines>", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(10, TimeUnit.SECONDS);
    }
        public void purgeMessages (TextChannel channel,int number){
        MessageHistory history = new MessageHistory(channel);
        List<Message> messages;

        messages = history.retrievePast(number).complete();
        channel.deleteMessages(messages).queue();
    }
}
