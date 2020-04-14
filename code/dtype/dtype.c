#include <stdio.h>
short add1(short x, short y) {
	return x + y;
}
int add2(int x, int y) {
	return x + y;
}
float add3(float x, float y) {
	return x + y;
}
double add4(double x, double y) {
	return x + y;
}
int main() {
	printf("%hd %d %f %lf\n", add1(4, 5), add2(3, 4), add3(9.0, 1.9), add4(8.0, 3.1));
	return 0;
}
