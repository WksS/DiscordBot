package br.com.wkss.bot.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class HelpCommand extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

      String help = event.getMessage().getContentRaw();

      if(help.equalsIgnoreCase(".help")) {
          EmbedBuilder help1 = new EmbedBuilder();
          help1.setDescription("Commands: " + System.lineSeparator() + ".help - Get help for the commands."
                  + System.lineSeparator() + ".clear - Clean it users and channels messages."
                  + System.lineSeparator() + ".online - Find out how many users are online right now."
                  + System.lineSeparator() + System.lineSeparator() + ".mute - Mute the users."
                  + System.lineSeparator() + "Bot developed by WksS.");
          event.getChannel().sendMessage(help1.build()).queue();
      }
    }
}
