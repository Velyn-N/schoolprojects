#pragma once
#include<string>

using namespace std;

class Datum
{
	public:
		Datum();
		Datum(int year, int month, int day);

		static Datum today();

		int getYear();
		int getMonth();
		int getDay();

		bool setYear(int year);
		bool setMonth(int month);
		bool setDay(int day);
		bool setDatum(int year, int month, int day);
		bool setDatum();

		bool isLeapYear();
		bool validate();

		static Datum create();
		Datum setYearF(int year);
		Datum setMonthF(int month);
		Datum setDayF(int day);

		bool isEqual(Datum datum);
		bool isLessThan(Datum datum);
		bool isMoreThan(Datum datum);

		bool operator==(const Datum& datum);
		bool operator<(const Datum& datum);
		bool operator>(const Datum& datum);

		string toString();
		string toSqlDateString();

	private:
		int year;
		int month;
		int day;
};

