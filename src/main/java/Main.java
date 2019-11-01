import org.quteshell.Command;
import org.quteshell.Quteshell;
import org.quteshell.commands.Help;
import spinupavm.ProgramManager;
import spinupavm.commands.*;

import java.net.ServerSocket;
import java.util.ArrayList;

public class Main {

    private static final int PORT = 3457;
    private static final ArrayList<Quteshell> quteshells = new ArrayList<>();

    private static boolean listening = true;

    public static void main(String[] args) {
        Quteshell.Configuration.setLogState(true);
        Quteshell.Configuration.setName("0w0");

        for (Class<? extends Command> command : Quteshell.Configuration.Commands.getCommands())
            Quteshell.Configuration.Commands.remove(command);

        Quteshell.Configuration.Commands.add(Help.class);
        Quteshell.Configuration.Commands.add(execute.class);
        Quteshell.Configuration.Commands.add(add.class);
        Quteshell.Configuration.Commands.add(compare.class);
        Quteshell.Configuration.Commands.add(move.class);
        Quteshell.Configuration.Commands.add(pop.class);
        Quteshell.Configuration.Commands.add(print.class);
        Quteshell.Configuration.Commands.add(push.class);
        Quteshell.Configuration.Commands.add(subtract.class);
        Quteshell.Configuration.Commands.add(wtf.class);

        Quteshell.Configuration.setIDLength(5);

        Quteshell.Configuration.setOnConnect(shell -> {
            shell.writeln("------------------------------------------", Quteshell.Color.LightRed);
            shell.writeln("---- Hello there, " + shell.getIdentifier() + ". Starting VM. ----", Quteshell.Color.LightOrange);
            shell.writeln("------------------------------------------", Quteshell.Color.LightRed);
            ProgramManager.start(shell);
        });

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (listening) {
                quteshells.add(new Quteshell(serverSocket.accept()));
            }
        } catch (Exception e) {
            System.out.println("Host - " + e.getMessage());
        }
    }
}
