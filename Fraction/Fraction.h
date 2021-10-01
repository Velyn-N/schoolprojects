#pragma once
#include <string>
using namespace std;
/**
 * @author Nils M, Mark F
 */
class Fraction
{
	public:
		Fraction(double x);
		Fraction(int numerator, int denominator);

		static int greatestCommonDivisor(int x, int y);
		static Fraction toFraction(double x);

		int getNumerator();
		int getDenominator();

		void setNumerator(int n);
		void setDenominator(int d);

		void add(Fraction f);
		void subtract(Fraction f);
		void multiply(Fraction f);
		void divideBy(Fraction f);

		void add(double d);
		void subtract(double d);
		void multiply(double d);
		void divideBy(double d);

		Fraction operator+(const Fraction fraction);
		Fraction operator-(const Fraction fraction);
		Fraction operator*(const Fraction fraction);
		Fraction operator/(const Fraction fraction);
		
		Fraction operator+(const double d);
		Fraction operator-(const double d);
		Fraction operator*(const double d);
		Fraction operator/(const double d);

		string toString();

		friend ostream& operator<<(ostream& out, Fraction fraction);
		friend istream& operator>>(istream& in, Fraction fraction);

	private:
		void shorten();

		int numerator;
		int denominator;
};

