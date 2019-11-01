package spinupavm.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Quteshell;
import spinupavm.ProgramManager;

@Elevation(1)
public class execute implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        shell.writeln("Preparing to run...", Quteshell.Color.LightOrange);
        ProgramManager.run(shell);
        shell.writeln("All done.", Quteshell.Color.LightOrange);
    }
}
