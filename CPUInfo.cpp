#include <iostream>
#include <string>
#include <intrin.h>

int main()
{
    int regs[4] = { -1 };

    char vendor[12];
    __cpuid(regs, 0);
    ((int*)vendor)[0] = regs[1];
    ((int*)vendor)[1] = regs[3];
    ((int*)vendor)[2] = regs[2];
    std::string cpuVendor = std::string(vendor, 12);

    char CPUBrandString[0x41];
    memset(CPUBrandString, 0, sizeof(CPUBrandString));

    __cpuid(regs, 0x80000002);
    memcpy(CPUBrandString, regs, sizeof(regs));

    __cpuid(regs, 0x80000003);
    memcpy(CPUBrandString + 16, regs, sizeof(regs));

    __cpuid(regs, 0x80000004);
    memcpy(CPUBrandString + 32, regs, sizeof(regs));

    std::cout << "name:" << CPUBrandString << std::endl;

    __cpuid(regs, 1);

    unsigned int family = (regs[0] >> 8) & 0xf;
    unsigned int model = (regs[0] >> 4) & 0xf;
    unsigned int extended_family = (regs[0] >> 20) & 0xff;
    unsigned int extended_model = (regs[0] >> 16) & 0xf;
    unsigned int stepping = regs[0] & 0xf;

    printf("family:%X\n", family);  

    printf("model:%X\n", model);

    printf("extendedfamily:%X\n", (extended_family | family));

    printf("extendedmodel:%X\n", ((extended_model << 4) | model));

    printf("stepping:%X\n", stepping);

    int logical = (regs[1] >> 16) & 0xFF;
    int physical = logical;

    if (cpuVendor == "GenuineIntel")
    {
        __cpuidex(regs, 0xB, 0);
        int logicaPerPhysical = regs[1];

        __cpuidex(regs, 0xB, 1);
        logical = regs[1];
        physical = logical / logicaPerPhysical;
        
    }
    else if (cpuVendor == "AuthenticAMD")
    {
        __cpuid(regs, 0x80000008);
        physical = ((unsigned)(regs[2] & 0xff)) + 1;
    }

    std::cout << "logical:" << logical << std::endl;

    std::cout << "physical:" << physical << std::endl;

    return 0;
}