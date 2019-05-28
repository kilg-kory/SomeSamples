package ru.kilg.ss.run.socket.nio_version;

/**
 * SomeSamples
 * Spinner - [Description]
 *
 * @author KIlG
 * @version 0.1
 * Create 25.05.19
 */
public class Spinner {

    private String currentSpinnerPart = "/";
    private boolean started = false;



    public void printNext() {
        if (!started) {
            System.out.print(" ");
            started = true;
        }

        clear();
        System.out.print(currentSpinnerPart);

        switch (currentSpinnerPart) {
            case "/": {
                currentSpinnerPart = "\u2014";
                break;
            }
            case "\u2014": {
                currentSpinnerPart = "\\";
                break;
            }
            case "\\": {
                currentSpinnerPart = "|";
                break;

            }
            default: {
                currentSpinnerPart = "/";
            }
        }
    }

    public void clear() {
        if (started)
            System.out.print("\b");
    }

}
