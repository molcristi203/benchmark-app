#include <iostream>
#include <chrono>
#include <algorithm>

int MEMORY_BLOCK_SIZE = 1024 * 1024 * 1024;

int main(int argc, char** argv)
{
	char* source = new char[MEMORY_BLOCK_SIZE];
	char* destination = new char[MEMORY_BLOCK_SIZE];

	memset(source, 100, MEMORY_BLOCK_SIZE);

	int runs = 20;

	if (argc > 2)
	{
		runs = atoi(argv[1]);
		MEMORY_BLOCK_SIZE = atoi(argv[2]);
	}

	int* durations = new int[runs];

	for (int i = 0; i < runs; i++)
	{
		auto start = std::chrono::system_clock::now();

		memcpy(destination, source, MEMORY_BLOCK_SIZE);

		auto end = std::chrono::system_clock::now();
		auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);

		durations[i] = duration.count();
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

	double transferSpeed = 1024 / timeInSeconds;

	std::cout << std::fixed << transferSpeed << std::endl;

	delete[] source;
	delete[] destination;
	delete[] durations;
}
