package br.com.wkss.bot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import br.com.wkss.bot.ClearCommand;
import br.com.wkss.bot.HelpCommand;
import br.com.wkss.bot.event.ListenerEvent;

import javax.security.auth.login.LoginException;

public class BotMain {

    private static JDABuilder builder;

    public static void main(String[] args) throws Exception {

        builder = new JDABuilder(AccountType.BOT);
        builder.setToken("NTAxNTMxODEwNjgyMTA5OTYy.Dqaviw.Jj_bEeyX4llEhnR39wNz_Ikwu1w");
        builder.setAutoReconnect(true);

        builder.addEventListener(new HelpCommand());
        builder.addEventListener(new ListenerEvent());
        builder.addEventListener(new ClearCommand());

        try {
            JDA jda = builder.buildBlocking();
        } catch (LoginException | InterruptedException | IllegalArgumentException exception) {
            exception.printStackTrace();
        }
    }
}
