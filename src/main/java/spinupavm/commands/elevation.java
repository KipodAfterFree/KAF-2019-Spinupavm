package spinupavm.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Quteshell;

@Elevation(Elevation.ALL)
public class elevation implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        shell.writeln("Elevation: "+shell.getElevation());
    }
}
