#include "Fraction.h"
#include <math.h>
#include <string>
#include <sstream>
using namespace std;

/**
 * @author Nils M, Mark F
 */
Fraction::Fraction(double x)
{
    int multipliedTimes = 0;
    while (x != (int)x) {
        x = x * 10;
        multipliedTimes++;
    }

    this->numerator = x;
    this->denominator = (int) pow(10, multipliedTimes);

    shorten();
}

Fraction::Fraction(int numerator, int denominator)
{
    this->numerator = numerator;
    this->denominator = denominator;
}

int Fraction::greatestCommonDivisor(int x, int y)
{
    int c;
    if (x < 0) x = -x;
    if (y < 0) y = -y;
    while (y != 0) {
        c = x % y; x = y; y = c;
    }
    return x;
}

Fraction Fraction::toFraction(double x)
{
    return Fraction(x);
}

int Fraction::getNumerator()
{
    return this->numerator;
}

int Fraction::getDenominator()
{
    return this->denominator;
}

void Fraction::setNumerator(int n)
{
    this->numerator = n;
}

void Fraction::setDenominator(int d)
{
    this->denominator = d;
}

void Fraction::add(Fraction f)
{
    this->numerator = this->numerator * f.getDenominator() + f.getNumerator() * this->denominator;
    this->denominator = this->denominator * f.getDenominator();
    shorten();
}

void Fraction::subtract(Fraction f)
{
    this->numerator = this->numerator * f.getDenominator() - f.getNumerator() * this->denominator;
    this->denominator = this->denominator * f.getDenominator();
    shorten();
}

void Fraction::multiply(Fraction f)
{
    this->numerator *= f.getNumerator();
    this->denominator *= f.getDenominator();
    shorten();
}

void Fraction::divideBy(Fraction f)
{
    int i = (f.getNumerator() < 0) ? 1 : -1;
    
    this->numerator *= f.getDenominator() * i;
    this->denominator *= f.getNumerator() * i;
    shorten();
}

void Fraction::add(double d)
{
    this->add(Fraction(d));
}

void Fraction::subtract(double d)
{
    this->subtract(Fraction(d));
}

void Fraction::multiply(double d)
{
    this->multiply(Fraction(d));
}

void Fraction::divideBy(double d)
{
    this->divideBy(Fraction(d));
}

Fraction Fraction::operator+(const Fraction fraction)
{
    this->add(fraction);
    return *this;
}

Fraction Fraction::operator-(const Fraction fraction)
{
    this->subtract(fraction);
    return *this;
}

Fraction Fraction::operator*(const Fraction fraction)
{
    this->multiply(fraction);
    return *this;
}

Fraction Fraction::operator/(const Fraction fraction)
{
    this->divideBy(fraction);
    return *this;
}

Fraction Fraction::operator+(const double d)
{
    this->add(d);
    return *this;
}

Fraction Fraction::operator-(const double d)
{
    this->subtract(d);
    return *this;
}

Fraction Fraction::operator*(const double d)
{
    this->multiply(d);
    return *this;
}

Fraction Fraction::operator/(const double d)
{
    this->divideBy(d);
    return *this;
}

string Fraction::toString()
{
    string s = string();
    ostringstream ostream;
    ostream << this->getNumerator() << "/" << this->getDenominator() << flush;
    return ostream.str();
}

void Fraction::shorten()
{
    int gcd = Fraction::greatestCommonDivisor(this->numerator, this->denominator);
    this->numerator = this->numerator / gcd;
    this->denominator = this->denominator / gcd;
}

ostream& operator<<(ostream& out, Fraction fraction)
{
    out << fraction.toString();
    return out;
}

istream& operator>>(istream& in, Fraction fraction)
{
    string temp;
    in >> temp;

    string numerator = temp.substr(0, temp.find("/"));
    string denominator = temp.substr(temp.find("/") + 1, temp.length()-1);

    fraction.setNumerator(stoi(numerator));
    fraction.setDenominator(stoi(denominator));
    return in;
}
