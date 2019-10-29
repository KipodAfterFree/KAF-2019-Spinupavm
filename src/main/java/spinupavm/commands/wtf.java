package spinupavm.commands;

import org.quteshell.Command;
import org.quteshell.Quteshell;

public class wtf implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        shell.writeln("Stacks: SA, SB", Quteshell.Color.LightPurple);
        shell.writeln("Registers: RA, RB, RC (RC holds the result for all operations)", Quteshell.Color.LightPurple);
    }
}
