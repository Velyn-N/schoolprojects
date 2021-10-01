#include <iostream>
#include "Fraction.h"

using namespace std;

int main()
{
    Fraction f1(0.5);
    Fraction f2(1, 3);

    cout << f1.toString();
    cout << "\n";
    cout << f2.toString();
    cout << "\n";
    
    f2 = f2 * f1;

    cout << f1.toString();
    cout << "\n";
    cout << f2.toString();
    cout << "\n";
}
