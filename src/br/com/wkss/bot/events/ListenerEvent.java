package br.com.wkss.bot;

import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ListenerEvent extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase("bot")) {
            if (!event.getMember().getUser().isBot()) {
                event.getChannel().sendMessage("Oi " + event.getMember().getUser().getName()).queue();
            }
        }
    }
}
