// RAMInfo.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include <Windows.h>

int main()
{
	ULONGLONG totalMemory;

	if (GetPhysicallyInstalledSystemMemory(&totalMemory)) 
	{
		std::cout << totalMemory / (static_cast<unsigned long long>(1024) * 1024) << std::endl;
	}
	else
	{
		std::cerr << "Failed to retrieve memory info" << std::endl;
	}

	return 0;
}
