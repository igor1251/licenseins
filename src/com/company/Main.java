package com.company;

public class Main {

    public static Command[] initializeArgumentBase()
    {
        Command[] availableCommandsList = new Command[5];
        availableCommandsList[0] = new Command("mode", "m", new String[] {"ins", "del", "change"});
        availableCommandsList[1] = new Command("file", "f", null);
        availableCommandsList[2] = new Command("license", "l", null);
        availableCommandsList[3] = new Command("old", "o", null);
        availableCommandsList[4] = new Command("help", "h", null);
        return availableCommandsList;
    }

    public static void printAvailableCommandsList(Command[] commandList)
    {
        for (Command cmd: commandList)
        {
            if (cmd != null) {
                System.out.println(cmd.getCommandFullName());
                System.out.println(cmd.getCommandShortName());
                if (cmd.getArguments() != null) {
                    for (String str : cmd.getArguments()) {
                        System.out.println("\t" + str);
                    }
                }
                else System.out.println("\t*not set*");
            }
        }
    }

    public static void main(String[] args) {
        Command[] commands = initializeArgumentBase();
        //printAvailableCommandsList(commands);

        for (String arg: args) {
            if (arg.indexOf("-") > -1 && arg.indexOf("--") < 0) {
                for (Command cmd: commands) {
                    if (arg.substring(1).equals(cmd.getCommandShortName())) {
                        switch (cmd.getCommandShortName()) {
                            case ("m"): System.out.println("mode command, short definition"); break;
                            case ("f"): System.out.println("file command, short definition"); break;
                            case ("l"): System.out.println("license command, short definition"); break;
                            case ("o"): System.out.println("old command, short definition"); break;
                            case ("h"): System.out.println("help command, short definition"); break;
                        }
                        break;
                    }
                }
            }
            else if (arg.indexOf("--") > -1) {
                for (Command cmd: commands) {
                    if (arg.substring(2).equals(cmd.getCommandFullName())) {
                        switch (cmd.getCommandFullName()) {
                            case ("mode"): System.out.println("mode command, full definition"); break;
                            case ("file"): System.out.println("file command, full definition"); break;
                            case ("license"): System.out.println("license command, full definition"); break;
                            case ("old"): System.out.println("old command, full definition"); break;
                            case ("help"): System.out.println("help command, full definition"); break;
                        }
                        break;
                    }
                }
            }
        }
    }
}
