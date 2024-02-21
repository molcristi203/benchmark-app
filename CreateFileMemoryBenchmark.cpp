#include <iostream>
#include <fstream>
#include <chrono>
#include <algorithm>
#include <string>

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

	std::ofstream file(fileLocation, std::ios::binary);
	if (!file.is_open())
	{
		std::cout << "Can't open file" << std::endl;
		return 1;
	}

	char* dataBlock = new char[BLOCK_SIZE];

	memset(dataBlock, 64, BLOCK_SIZE);

	int* durations = new int[runs];

	for (int j = 0; j < runs; j++)
	{
		auto start = std::chrono::system_clock::now();

		for (int i = 0; i < BLOCK_COUNT; i++)
		{
			file.write(dataBlock, BLOCK_SIZE);
		}

		auto end = std::chrono::system_clock::now();

		auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);

		durations[j] = duration.count();

		file.seekp(0, std::ios::beg);
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

	file.flush();

	file.close();

	return 0;
}
