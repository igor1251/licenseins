package com.company;

import java.util.ArrayList;

public class Main {

    public static command[] initializeArgumentBase()
    {
        command[] availableCommandsList = new command[5];
        availableCommandsList[0] = new command("mode", "m", new String[] {"ins", "del", "change"});
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
                if (cmd.getArguments() != null) {
                    for (String str : cmd.getArguments()) {
                        System.out.println("\t" + str);
                    }
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
            if (arg.indexOf("-") > -1 && arg.indexOf("--") < 0) {
                commands.add(new command(null, arg.substring(1), null));
            }
            else if (arg.indexOf("--") > -1) {
                commands.add(new command(arg.substring(2), null, null));
            }
            else {
                if (!commands.isEmpty()) {
                    commands.get(commands.size() - 1).setArguments(arg);
                }
                else {
                    System.out.println("The arguments are specified incorrectly!");
                    return null;
                }
            }
        }
        return commands;
    }

    public static void main(String[] args) {
        ArrayList<command> commands = parseArguments(args);
        checkCommands(commands, initializeArgumentBase());
        
    }
}
