#include <iostream>
#include "Datum.h"

using namespace std;

int main()
{
    Datum today = Datum::today();
    Datum niceDate = Datum::create().setYearF(420).setMonthF(9).setDayF(6);

    cout << "today: ";
    cout << today.toString() << endl;
    
    cout << "niceDate: ";
    cout << niceDate.toString() << endl;

    cout << "today.isLessThan(niceDate): ";
    if (today.isLessThan(niceDate)) {
        cout << "Ja" << endl;
    }
    else {
        cout << "Nein" << endl;
    }

    cout << "today < niceDate: ";
    if (today < niceDate) {
        cout << "Ja" << endl;
    }
    else {
        cout << "Nein" << endl;
    }
}
