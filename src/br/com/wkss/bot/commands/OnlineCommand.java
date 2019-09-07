package br.com.wkss.bot.commands;

import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class OnlineCommand extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(".online")) {
            int online = 0;
            for(int i = 0; i < event.getGuild().getMembers().size(); i++) {
                if(event.getGuild().getMembers().get(i).getOnlineStatus() == OnlineStatus.ONLINE || event.getGuild().getMembers().get(i).getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB) {
                    online++;
                }
            }
            event.getChannel().sendMessage("They are online at the moment " + online + " users online of " + (event.getGuild().getMembers().size()-1)).queue();
        }
    }
}
