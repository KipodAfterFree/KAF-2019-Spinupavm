package spinupavm.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Quteshell;
import org.quteshell.commands.Help;

@Elevation(1)
@Help.Description("print - prints the value at register arg1")
public class prt implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {

    }
}
