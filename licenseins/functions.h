#pragma once

using namespace std;

struct Command
{
public:
	string commandShortName;
	string commandFullName;
	string* arguments;
};

void Setup(Command* commandList);