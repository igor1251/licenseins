#define _CRT_SECURE_NO_WARNINGS

#include <iostream>
#include "functions.h"

void Setup(Command* commandList)
{
	const unsigned int commandsCount = 5;
	commandList = new Command[commandsCount];

	commandList[0].commandFullName = "mode";
	commandList[0].commandShortName = "m";
	commandList[0].arguments = new string[3]{};
	commandList[0].arguments[0] = "ins";
	commandList[0].arguments[1] = "del";
	commandList[0].arguments[2] = "change";


	/*strcpy(commandList[0].commandFullName, "mode");
	strcpy(commandList[0].commandShortName, "m");
	
	strcpy(commandList[0].commandFullName, "mode");
	strcpy(commandList[0].commandShortName, "m");

	strcpy(commandList[0].commandFullName, "mode");
	strcpy(commandList[0].commandShortName, "m");

	strcpy(commandList[0].commandFullName, "mode");
	strcpy(commandList[0].commandShortName, "m");*/
}