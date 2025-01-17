package spinupavm.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Quteshell;

@Elevation(1)
public class wtf implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        shell.writeln("Stacks:      SA, SB (20 and 10 respectively)", Quteshell.Color.LightPurple);
        shell.writeln("Registers:   RA, RB, RC (RC holds the result for all operations, starts at 1)", Quteshell.Color.LightPurple);
    }
}
