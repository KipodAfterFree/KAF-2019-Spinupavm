import org.quteshell.Command;
import org.quteshell.Quteshell;
import org.quteshell.commands.Exit;
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
        Quteshell.Configuration.setName("?");

        for (Class<? extends Command> command : Quteshell.Configuration.Commands.getCommands())
            Quteshell.Configuration.Commands.remove(command);

        Quteshell.Configuration.Commands.add(Help.class);
        Quteshell.Configuration.Commands.add(Exit.class);

        Quteshell.Configuration.Commands.add(reset.class);
        Quteshell.Configuration.Commands.add(execute.class);
        Quteshell.Configuration.Commands.add(add.class);
        Quteshell.Configuration.Commands.add(cmp.class);
        Quteshell.Configuration.Commands.add(mov.class);
        Quteshell.Configuration.Commands.add(pop.class);
        Quteshell.Configuration.Commands.add(prt.class);
        Quteshell.Configuration.Commands.add(psh.class);
        Quteshell.Configuration.Commands.add(sub.class);
        Quteshell.Configuration.Commands.add(wtf.class);

        Quteshell.Configuration.setIDLength(5);

        Quteshell.Configuration.setOnConnect(shell -> {
            shell.writeln("------------------------------------------", Quteshell.Color.LightRed);
            shell.writeln("---- Hello there, " + shell.getIdentifier() + ". Starting VM. ----", Quteshell.Color.LightOrange);
            shell.writeln("---- We record errors, to improve UX. ----", Quteshell.Color.LightOrange);
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
