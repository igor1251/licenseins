package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void printUsage() {
        System.out.println("USAGE:\n--mode [-m] <ins | del | change> --file [-f] <fileName> --license [-l] <licenseFile> [--old [-o] <oldLicenseFileName>]");
    }

    public static Command[] initializeArgumentBase()
    {
        Command[] availableCommandsList = new Command[5];
        availableCommandsList[0] = new Command("mode", "m", null);
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
                if (cmd.getArgument() != null) {
                    System.out.println("\t" + cmd.getArgument());
                }
                else System.out.println("\t*not set*");
            }
        }
    }

    public static void checkCommands(ArrayList<Command> commands, Command[] controlCommandList)
    {
        boolean commandFound = false;
        String commandText = "";
        for (Command cmd: commands) {
            commandFound = false;
            if (cmd.getCommandFullName() != null) {
                commandText = cmd.getCommandFullName();
            }
            else if (cmd.getCommandShortName() != null) {
                commandText = cmd.getCommandShortName();
            }
            for (Command controlCommand: controlCommandList) {
                if (controlCommand.getCommandFullName().equals(commandText) || controlCommand.getCommandShortName().equals(commandText)) {
                    commandFound = true;
                    break;
                }
            }
            if (!commandFound) {
                System.out.println("The arguments are specified incorrectly!\nCommand \"" + commandText + "\" is not expected.");
                printUsage();
                System.exit(1);
            }
        }
    }

    public static ArrayList<Command> parseArguments(String[] args)
    {
        ArrayList<Command> commands = new ArrayList<Command>();
        for (String arg: args) {
            if (arg.contains("-") && !arg.contains("--")) {
                commands.add(new Command(null, arg.substring(1), null));
            }
            else if (arg.contains("--")) {
                commands.add(new Command(arg.substring(2), null, null));
            }
            else {
                if (!commands.isEmpty()) {
                    commands.get(commands.size() - 1).setArgument(arg);
                }
                else {
                    System.out.println("The arguments are specified incorrectly!");
                    printUsage();
                    System.exit(1);
                }
            }
        }
        return commands;
    }

    public static Command searchCommand(String shortName, String fullName, ArrayList<Command> enteredCommands) {
        for (Command cmd: enteredCommands) {
            if (cmd.getCommandShortName() != null && cmd.getCommandShortName().equals(shortName) || cmd.getCommandFullName() != null && cmd.getCommandFullName().equals(fullName)) {
                return cmd;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            printUsage();
            System.exit(0);
        }

        ArrayList<Command> commands = parseArguments(args);
        checkCommands(commands, initializeArgumentBase());

        Command modeCommand = searchCommand("m", "mode", commands);
        if (modeCommand != null) {
            Command fileCommand = searchCommand("f", "file", commands);
            if (fileCommand == null) {
                System.out.println("Command \"file\" not set!");
                printUsage();
                return;
            }

            Command licenseFileCommand = searchCommand("l", "license", commands);
            if (licenseFileCommand == null) {
                System.out.println("Command \"license\" not set!");
                printUsage();
                return;
            }

            switch (modeCommand.getArgument()) {
                case ("ins"):
                    LicenseActions.insertLicenseInfo(fileCommand.getArgument(), licenseFileCommand.getArgument());
                    break;
                case ("del"):
                    LicenseActions.deleteLicenseInfo(fileCommand.getArgument(), licenseFileCommand.getArgument());
                    break;
                case ("change"):
                    Command oldLicenseFileCommand = searchCommand("o", "old", commands);
                    if (oldLicenseFileCommand == null) {
                        System.out.println("Command \"old\" not set!");
                        printUsage();
                        return;
                    }
                    LicenseActions.replaceLicenseInfo(fileCommand.getArgument(), oldLicenseFileCommand.getArgument(), licenseFileCommand.getArgument());
                    break;
                default:
                    System.out.println("Command \"mode\" set incorrectly!");
                    printUsage();
            }
        }
        else {
            System.out.println("Command \"mode\" not set!");
            printUsage();
            System.exit(1);
        }
    }
}