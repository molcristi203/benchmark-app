#include <windows.h>
#include <iostream>
#include <chrono>
#include <algorithm>
#include <string>
#include <fileapi.h>

int BLOCK_COUNT = 10240;
int BLOCK_SIZE = 1024 * 1024;

int main(int argc, char** argv)
{
	int runs = 1;
	std::string fileLocation = "";

	if (argc > 4)
	{
		runs = atoi(argv[1]);
		fileLocation = argv[2];
		BLOCK_COUNT = atoi(argv[3]);
		BLOCK_SIZE = atoi(argv[4]);
	}
	else if (argc > 1)
	{
		runs = atoi(argv[1]);
	}

	/*std::ifstream file;
	file.rdbuf()->pubsetbuf(0, 0);
	file.open(fileLocation);*/

	/*if (!file.is_open())
	{
		std::cout << "Can't open file" << std::endl;
		return 1;
	}*/

	HANDLE file = CreateFileA(fileLocation.c_str(), GENERIC_READ, FILE_SHARE_READ, NULL, OPEN_EXISTING, FILE_FLAG_NO_BUFFERING | FILE_FLAG_SEQUENTIAL_SCAN, NULL);
	
	if (file == INVALID_HANDLE_VALUE)
	{
		std::cout << "Can't open file" << std::endl;
	}
	
	char* dataBlock = new char[BLOCK_SIZE];

	int* durations = new int[runs];

	for (int j = 0; j < runs; j++)
	{
		auto start = std::chrono::system_clock::now();

		for (int i = 0; i < BLOCK_COUNT; i++)
		{
			/*if (!file.read(dataBlock, BLOCK_SIZE))
			{
				std::cout << "Error reading from file" << std::endl;
				return 1;
			}*/

			DWORD bytesRead;
			if (!ReadFile(file, dataBlock, BLOCK_SIZE, &bytesRead, NULL))
			{
				std::cout << "Error reading from file" << std::endl;
				CloseHandle(file);
				return 1;
			}
		}

		auto end = std::chrono::system_clock::now();

		auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);

		durations[j] = duration.count();

		//file.seekg(0, std::ios::beg);

		SetFilePointer(file, 0, NULL, FILE_BEGIN);
	}

	std::sort(durations, durations + runs);

	double timeInSeconds = 0.0f;

	if (runs % 2 == 0)
	{
		timeInSeconds = ((double(durations[runs / 2]) + double(durations[runs / 2 - 1])) / 2.0f) / 1000;
	}
	else
	{
		timeInSeconds = double(durations[runs / 2]) / 1000.0f;
	}

	double transferSpeed = double(BLOCK_COUNT) * BLOCK_SIZE / (1024 * 1024) / timeInSeconds;

	std::cout << std::fixed << transferSpeed << std::endl;

	delete[] dataBlock;
	delete[] durations;

	//file.close();

	CloseHandle(file);

	std::remove((fileLocation).c_str());

	return 0;
}
