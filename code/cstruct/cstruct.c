#include <stdio.h>
int main() {
	if (1 > 2) {
		printf("this will not be printed\n");
	}
	else
		printf("this will be printed\n");
	for (int i = 0; i < 2; i++) {
		printf("%d\n", i);
	}
	return 0;
}
