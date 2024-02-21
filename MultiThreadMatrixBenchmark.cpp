#include <iostream>
#include <thread>
#include <intrin.h>
#include <chrono>
#include <stdalign.h> 

struct ClockCycles { alignas(64) __int64 v; } *clockCycles;
struct TimeDurations { alignas(std::chrono::milliseconds) std::chrono::milliseconds v; } *durations;

void initializeResultMatrix(int n, int**& result)
{
	result = new int* [n];

	for (int i = 0; i < n; i++)
	{
		result[i] = new int[n];
		for (int j = 0; j < n; j++)
		{
			result[i][j] = 0;
		}
	}
}

void initializeMatrices(int n, int** &mat1, int** &mat2)
{
	mat1 = new int* [n];
	mat2 = new int* [n];

	for (int i = 0; i < n; i++)
	{
		mat1[i] = new int[n];
		mat2[i] = new int[n];
		for (int j = 0; j < n; j++)
		{
			mat1[i][j] = i;
			mat2[i][j] = j;
		}
	}
}

void initializeAlignedArrays(int threadsNumber)
{
	size_t size1 = sizeof(clockCycles[0]);
	clockCycles = (ClockCycles*)(_aligned_malloc(threadsNumber * size1, size1));
	for (int i = 0; i < threadsNumber; i++)
	{
		clockCycles[i].v = 0;
	}

	size_t size2 = sizeof(durations[0]);
	durations = (TimeDurations*)(_aligned_malloc(threadsNumber * size2, size2));
	for (int i = 0; i < threadsNumber; i++)
	{
		durations[i].v = std::chrono::milliseconds(0);
	}
}

void multiply(int startR1, int endR1, int c1, int c2, int** mat1, int** mat2, int** &result)
{
	for (int i = startR1; i < endR1; i++)
	{
		for (int j = 0; j < c2; j++)
		{
			for (int k = 0; k < c1; k++)
			{
				result[i][j] += mat1[i][k] * mat2[k][j];
			}
		}
	}
}

void printMatrix(int n, int** mat)
{
	for (int i = 0; i < n; i++)
	{
		for (int j = 0; j < n; j++)
		{
			std::cout << mat[i][j] << " ";
		}
		std::cout << std::endl;
	}
}

void dealocate(int n, int** &mat1, int** &mat2, int** &result)
{
	for (int i = 0; i < n; i++)
	{
		free(mat1[i]);
		free(mat2[i]);
		free(result[i]);
	}

	free(mat1);
	free(mat2);
	free(result);

	_aligned_free(clockCycles);
	_aligned_free(durations);
}

void threadFunction(int i, int startR1, int endR1, int c1, int c2, int** mat1, int** mat2, int**& result)
{
	auto start = std::chrono::system_clock::now();

	unsigned __int64 startCycles = __rdtsc();

	multiply(startR1, endR1, c1, c2, mat1, mat2, result);

	unsigned __int64 endCycles = __rdtsc();

	auto end = std::chrono::system_clock::now();

	clockCycles[i].v += endCycles - startCycles;

	auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);

	durations[i].v += duration;
}

void createThreads(int threadsNumber, int n, int** mat1, int** mat2, int** &result)
{
	std::thread* threads = new std::thread[threadsNumber];

	if (threads == nullptr)
	{
		std::cout << "nu mere :((" << std::endl;
		return;
	}

	int rowsPerThread = n / threadsNumber;
	for (int i = 0; i < threadsNumber - 1; i++)
	{
		threads[i] = std::thread(threadFunction, i, i * rowsPerThread, (i + 1) * rowsPerThread, n, n, mat1, mat2, std::ref(result));
	}

	threads[threadsNumber - 1] = std::thread(threadFunction, threadsNumber - 1, (threadsNumber - 1) * rowsPerThread , n, n, n, mat1, mat2, std::ref(result));

	for (int i = 0; i < threadsNumber; i++)
	{
		threads[i].join();
	}

	delete[] threads;
}

void computeFinalValues(int threadsNumber, int runs)
{
	std::chrono::milliseconds totalMiliseconds = std::chrono::milliseconds(0);
	__int64 totalCycles = 0;

	for (int i = 0; i < threadsNumber; i++)
	{
		totalMiliseconds += durations[i].v;
		totalCycles += clockCycles[i].v;
	}

	std::cout << "milliseconds:" << (double)totalMiliseconds.count() / threadsNumber / runs << std::endl;;
	std::cout << "cycles:" << totalCycles / threadsNumber / runs << std::endl;
}

int main(int argc, char **argv)
{
	int n = 1000;
	int runs = 4;
	int threadsNumber = std::thread::hardware_concurrency();

	if (argc > 2)
	{
		n = atoi(argv[1]);
		runs = atoi(argv[2]);
	}

	int** mat1;
	int** mat2;
	int** result;
	initializeMatrices(n, mat1, mat2);
	/*printMatrix(n, mat1);
	std::cout << std::endl;
	printMatrix(n, mat2);
	std::cout << std::endl;
	printMatrix(n, result);
	std::cout << std::endl;*/

	initializeAlignedArrays(threadsNumber);

	for (int i = 0; i < runs; i++)
	{
		initializeResultMatrix(n, result);
		createThreads(threadsNumber, n, mat1, mat2, result);
	}

	/*printMatrix(n, result);
	std::cout << std::endl;*/

	computeFinalValues(threadsNumber, runs);

	dealocate(n, mat1, mat2, result);

	return 0;
}