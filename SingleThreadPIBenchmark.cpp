#include <iostream>
#include <cmath>
#include <iomanip>
#include <chrono>
#include "mpreal.h"
#include <intrin.h>
#include <algorithm>

#pragma intrinsic(__rdtsc)

using mpfr::mpreal;

void computePI(mpreal a0, mpreal b0, mpreal t0, mpreal p0, int n, mpreal& result) {
    mpreal a = a0;
    mpreal b = b0;
    mpreal t = t0;
    mpreal p = p0;

    for (int i = 0; i < n; i++) {
        mpreal a2 = (a + b) / 2.0;
        mpreal b2 = sqrt(a * b);
        mpreal t2 = t - p * pow((a - a2), 2);
        mpreal p2 = 2.0 * p;

        a = a2;
        b = b2;
        t = t2;
        p = p2;
    }

    result = pow(a + b, 2) / (4 * t);
}

int main(int argc, char** argv)
{
    mpreal::set_default_prec(mpfr::digits2bits(200));

	mpreal a0 = 1.0;
	mpreal b0 = 1.0 / mpfr::sqrt(2.0);
	mpreal t0 = 1.0 / 4.0;
	mpreal p0 = 1.0;

	int n = 1000000;
    int numberOfRuns = 10;
    unsigned int ui;

	if (argc > 2)
	{
		n = atoi(argv[1]);
        numberOfRuns = atoi(argv[2]);
	}

	mpreal result = 0;

    int* durations = new int[numberOfRuns];
    unsigned __int64* cycles = new unsigned __int64[numberOfRuns];

    for (int i = 0; i < numberOfRuns; i++)
    {
        auto start = std::chrono::system_clock::now();

        unsigned __int64 startCycles = __rdtscp(&ui);

        computePI(a0, b0, t0, p0, n, result);

        unsigned __int64 endCycles = __rdtscp(&ui);

        auto end = std::chrono::system_clock::now();

        auto duration = std::chrono::duration_cast<std::chrono::milliseconds>(end - start);

        durations[i] = duration.count();

        cycles[i] = endCycles - startCycles;
    }

    std::sort(durations, durations + numberOfRuns);
    std::sort(cycles, cycles + numberOfRuns);

    int durationOut;
    unsigned __int64 cyclesOut;

    if (numberOfRuns % 2 == 0)
    {
        durationOut = (durations[numberOfRuns / 2] + durations[numberOfRuns / 2 - 1]) / 2.0f;
        cyclesOut = (cycles[numberOfRuns / 2] + cycles[numberOfRuns / 2 - 1]) / 2.0f;
    }
    else
    {
        durationOut = durations[numberOfRuns / 2];
        cyclesOut = cycles[numberOfRuns] / 2;
    }

	//std::cout << result.toString(200) << std::endl;

    std::cout << "milliseconds:" << durationOut << std::endl;

    std::cout << "cycles:" << cyclesOut << std::endl;

    delete[] durations;
    delete[] cycles;
}