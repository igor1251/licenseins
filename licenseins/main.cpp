#include <iostream>
#include <fstream>
#include "functions.h"

using namespace std;

int main(int argc, char** argv)
{
	Command* commandList = new Command[1];;
	Setup(commandList);

	cout << commandList[0].commandFullName << endl << commandList[0].commandShortName << endl;
	for (int i = 0; i < 3; i++)
	{
		cout << commandList[0].arguments[i] << endl;
	}
}