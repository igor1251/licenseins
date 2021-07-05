package com.company;

public class command {
    String commandShortName;
    String commandFullName;
    String argument;

    public command(String commandFullName, String commandShortName, String argument)
    {
        this.commandFullName = commandFullName;
        this.commandShortName = commandShortName;
        this.argument = argument;
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

    public void setArgument(String argument)
    {
        this.argument = argument;
    }

    public String getArgument()
    {
        return argument;
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
