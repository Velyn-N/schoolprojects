#include "Datum.h"
#include <iostream>
#include <ctime>
#include <stdlib.h>
#include <sstream>

using namespace std;

Datum::Datum() {
    Datum(1970, 1, 1);
}

Datum::Datum(int year, int month, int day) {
    this->year = year;
    this->month = month;
    this->day = day;
}

Datum Datum::today()
{
    Datum d = Datum();
    d.setDatum();
    return d;
}

int Datum::getYear()
{
    return year;
}

int Datum::getMonth()
{
    return month;
}

int Datum::getDay()
{
    return day;
}



bool Datum::setYear(int year)
{
    this->year = year;
    return true;
}

bool Datum::setMonth(int month)
{
    this->month = month;
    return true; // Validation fehlt noch
}

bool Datum::setDay(int day)
{
    this->day = day;
    return true; // Validation fehlt noch
}

bool Datum::setDatum() {
    struct tm* now = new tm();
    time_t nowtime = time(0);
    localtime_s(now, &nowtime);

    year = now->tm_year;
    month = now->tm_mon;
    day = now->tm_mday;

    return validate();
}

bool Datum::setDatum(int year, int month, int day) {
    setYearF(year).setMonthF(month).setDay(day);
    return validate();
}

bool Datum::isLeapYear() {
    return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
}

bool Datum::validate() {
    if ((0 < month < 13) && day > 0) {
        if (month == 2) {
            if (isLeapYear()) {
                return (day <= 29);
            }
            return (day <= 28);
        }

        bool is30erMonth = (month == 4 || month == 6 || month == 9 || month == 11);
        if (is30erMonth && day <= 30) {
            return true;
        }
        bool is31erMonth = (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12);
        if (is31erMonth && day <= 31) {
            return true;
        }
    }
    return false;
}


Datum Datum::create()
{
    return Datum();
}

Datum Datum::setYearF(int year) {
    this->setYear(year);
    return *this;
}

Datum Datum::setMonthF(int month) {
    this->setMonth(month);
    return *this;
}

Datum Datum::setDayF(int day) {
    this->setDay(day);
    return *this;
}



bool Datum::isEqual(Datum datum)
{
    return (datum.getYear() == getYear()) &&
        (datum.getMonth() == getMonth()) &&
        (datum.getDay() == getDay());
}

bool Datum::isLessThan(Datum datum)
{
    if (getYear() < datum.getYear()) {
        return true;
    }
    else if (getYear() == datum.getYear()) {
        if (getMonth() < datum.getMonth()) {
            return true;
        }
        else if (getMonth() == datum.getMonth()) {
            return (getDay() < datum.getDay());
        }
    }
    return false;
}

bool Datum::isMoreThan(Datum datum) {
    if (getYear() > datum.getYear()) {
        return true;
    }
    else if (getYear() == datum.getYear()) {
        if (getMonth() > datum.getMonth()) {
            return true;
        }
        else if (getMonth() == datum.getMonth()) {
            return (getDay() > datum.getDay());
        }
    }
    return false;
}

bool Datum::operator==(const Datum& datum)
{
    return isEqual(datum);
}

bool Datum::operator<(const Datum& datum)
{
    return isLessThan(datum);
}

bool Datum::operator>(const Datum& datum)
{
    return isMoreThan(datum);
}



string Datum::toString()
{
    string s = string();
    ostringstream ostream;
    ostream << this->getDay() << "." << this->getMonth() << "." << this->getYear() << flush;
    return ostream.str();
}

string Datum::toSqlDateString()
{
    string s = string();
    ostringstream ostream;
    ostream << this->getYear() << "-" << this->getMonth() << "-" << this->getDay() << flush;
    return ostream.str();
}
