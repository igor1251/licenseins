package com.company;

public class command {
    String commandShortName;
    String commandFullName;
    String[] arguments;

    public command(String commandFullName, String commandShortName, String[] arguments)
    {
        this.commandFullName = commandFullName;
        this.commandShortName = commandShortName;
        this.arguments = arguments;
    }

    public void setCommandShortName(String commandShortName)
    {
        if (commandShortName != "" && commandShortName != null)
        {
            this.commandShortName = commandShortName;
        }
        else throw new IllegalArgumentException();
    }

    public void setCommandFullName(String commandFullName)
    {
        if (commandFullName != "" && commandFullName != null)
        {
            this.commandFullName = commandFullName;
        }
        else throw new IllegalArgumentException();
    }

    public void setArguments(String[] arguments)
    {
        this.arguments = arguments;
    }

    public void setArguments(String argument)
    {
        this.arguments = new String[1];
        this.arguments[0] = argument;
    }

    public String[] getArguments()
    {
        return arguments;
    }

    public String getCommandShortName()
    {
        return this.commandShortName;
    }

    public String getCommandFullName()
    {
        return this.commandFullName;
    }
}
