#include <iostream>
#include <Windows.h>
#include <string.h>

int main()
{
	DWORD cchBuffer;
	WCHAR* driveStrings;
	WCHAR* driveStrings2;
	UINT driveType;
	std::string driveTypeString;
	ULARGE_INTEGER freeSpace;
	ULARGE_INTEGER totalSpace;

	cchBuffer = GetLogicalDriveStringsW(0, NULL);
	
	driveStrings = (WCHAR*)malloc((static_cast<unsigned long long>(cchBuffer) + 1) * sizeof(WCHAR));
	if (driveStrings == NULL)
	{
		return -1;
	}

	driveStrings2 = driveStrings;

	GetLogicalDriveStringsW(cchBuffer, driveStrings);

	while (*driveStrings)
	{
		driveType = GetDriveTypeW(driveStrings);
		GetDiskFreeSpaceExW(driveStrings, &freeSpace, &totalSpace, NULL);

		switch (driveType)
		{
			case DRIVE_FIXED:
				driveTypeString = "HDD/SSD";
				break;
			case DRIVE_REMOVABLE:
				driveTypeString = "IO";
				break;
			default:
				break;
		}

		std::cout << driveTypeString << "-";
		std::wcout << driveStrings << " ";
		std::cout << freeSpace.QuadPart / 1024.0 / 1024.0 / 1024.0 << " " << totalSpace.QuadPart / 1024.0 / 1024.0 / 1024.0 << std::endl;

		driveStrings += lstrlen(driveStrings) + 1;
	}

	free(driveStrings2);

	return 0;
}