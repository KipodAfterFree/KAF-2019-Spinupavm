package spinupavm.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Quteshell;
import org.quteshell.commands.Help;

@Elevation(1)
@Help.Description("push - pushes the value at register RC to stack arg1")
public class psh implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {

    }
}
