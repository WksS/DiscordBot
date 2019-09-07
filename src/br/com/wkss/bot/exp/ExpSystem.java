package br.com.wkss.bot;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ExpSystem extends ListenerAdapter {

    HashMap<Member, Integer> playerXp = new HashMap<>();
    HashMap<Member, Integer> playerTimer = new HashMap<>();

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        String command = args[0];

        if (command.equalsIgnoreCase(".xp")) {
            event.getChannel().sendMessage("You have " + getPlayerXp(event.getMember()) + " xp.").queue();
        }

        if(canGetXp(event.getMember())) {
            randomXp(event.getMember());
            setPlayerTime(event.getMember(),3);
        }
    }

    private int getPlayerXp(Member member) {
        return playerXp.get(member);
    }

    private void setPlayerXp(Member member, int amount) {
        playerXp.put(member, amount);
    }

    private int getPlayerTime(Member member) {
        return playerTimer.get(member);
    }

    private void setPlayerTime(Member member, int amount) {
        playerTimer.put(member, amount);
    }

    private void randomXp(Member member) {
        Random random = new Random();
        if (!playerXp.containsKey(member)) {
            setPlayerXp(member, 0);
            setPlayerXp(member, getPlayerXp(member) + random.nextInt(25));
        }
    }

    private boolean canGetXp(Member member) {
        if (!playerTimer.containsKey(member)) {
            return true;
        }
        return false;
    }

    public void startTimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                for(Member member : playerTimer.keySet()) {
                    setPlayerTime(member, getPlayerTime(member) - 1);
                    if(getPlayerTime(member) == 0)
                        playerTimer.remove(member);
                }
            }
        };
        timer.schedule(timerTask, 1000, 1000);
    }
}