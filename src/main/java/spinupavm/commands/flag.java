package spinupavm.commands;

import org.quteshell.Command;
import org.quteshell.Elevation;
import org.quteshell.Quteshell;

@Elevation(Elevation.ALL)
public class flag implements Command {
    @Override
    public void execute(Quteshell shell, String arguments) {
        if (shell.getElevation() < 0) {
            shell.writeln("KAF{w3ll_p1ay3d_my_hack3r_fr13nd}");
        } else {
            shell.writeln("KAF{damn_1_0nly_had_t0_writ3_flag}");
        }
    }
}
