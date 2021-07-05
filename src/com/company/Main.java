package com.company;

import java.util.ArrayList;

public class Main {

    public static command[] initializeArgumentBase()
    {
        command[] availableCommandsList = new command[5];
        availableCommandsList[0] = new command("mode", "m", null);
        availableCommandsList[1] = new command("file", "f", null);
        availableCommandsList[2] = new command("license", "l", null);
        availableCommandsList[3] = new command("old", "o", null);
        availableCommandsList[4] = new command("help", "h", null);
        return availableCommandsList;
    }

    public static void printAvailableCommandsList(command[] commandList)
    {
        for (command cmd: commandList)
        {
            if (cmd != null) {
                System.out.println(cmd.getCommandFullName());
                System.out.println(cmd.getCommandShortName());
                if (cmd.getArgument() != null) {
                    System.out.println("\t" + cmd.getArgument());
                }
                else System.out.println("\t*not set*");
            }
        }
    }

    public static void checkCommands(ArrayList<command> commands, command[] controlCommandList)
    {
        boolean commandFound = false;
        String commandText = "";
        for (command cmd: commands) {
            commandFound = false;
            if (cmd.getCommandFullName() != null) {
                commandText = cmd.getCommandFullName();
            }
            else if (cmd.getCommandShortName() != null) {
                commandText = cmd.getCommandShortName();
            }
            for (command controlCommand: controlCommandList) {
                if (controlCommand.getCommandFullName().equals(commandText) || controlCommand.getCommandShortName().equals(commandText)) {
                    commandFound = true;
                    break;
                }
            }
            if (!commandFound) {
                System.out.println("The arguments are specified incorrectly!\nCommand \"" + commandText + "\" is not expected.");
                return;
            }
        }
    }

    public static ArrayList<command> parseArguments(String[] args)
    {
        ArrayList<command> commands = new ArrayList<command>();
        for (String arg: args) {
            if (arg.contains("-") && !arg.contains("--")) {
                commands.add(new command(null, arg.substring(1), null));
            }
            else if (arg.contains("--")) {
                commands.add(new command(arg.substring(2), null, null));
            }
            else {
                if (!commands.isEmpty()) {
                    commands.get(commands.size() - 1).setArgument(arg);
                }
                else {
                    System.out.println("The arguments are specified incorrectly!");
                    return null;
                }
            }
        }
        return commands;
    }

    public static command searchCommand(String shortName, String fullName, ArrayList<command> enteredCommands) {
        for (command cmd: enteredCommands) {
            if (cmd.getCommandShortName() != null && cmd.getCommandShortName().equals(shortName) || cmd.getCommandFullName() != null && cmd.getCommandFullName().equals(fullName)) {
                return cmd;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ArrayList<command> commands = parseArguments(args);
        checkCommands(commands, initializeArgumentBase());

        licenseActions actions = new licenseActions();

        command modeCommand = searchCommand("m", "mode", commands);
        if (modeCommand != null) {
            switch (modeCommand.getArgument()) {
                case ("ins"):
                    System.out.println("insert command selected");
                    break;
                case ("del"):
                    System.out.println("delete command selected");
                    break;
                case ("change"):
                    System.out.println("change command selected");
                    break;
            }
        }
        else {
            System.out.println("mode-command not set!");
            return;
        }
    }
}
