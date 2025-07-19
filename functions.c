#include <stdio.h>

void addition() {
    int a = 9, b = 5, c = 10, ans;
    ans = a + b + c;
    printf("The Addition of %d, %d and %d is %d\n", a, b, c, ans);
}

void addition1(int a, int b, int c) {
    int ans = a + b + c;
    printf("The Addition of %d, %d and %d is %d\n", a, b, c, ans);
}

int addition2(int a, int b, int c) {
    return a + b + c;
}

int addition3() {
    int a = 10, b = 15, c = 20;
    return a + b + c;
}

int main() {
    printf("Calling function with No Arguments and No Return Value:\n");
    addition();

    printf("\nCalling function with Arguments and No Return Value:\n");
    addition1(4, 5, 5);

    int x = 10, y = 15, z = 12;
    int add = addition2(x, y, z);
    printf("\nCalling function with Arguments and Return Value:\n");
    printf("The Addition of %d, %d and %d is %d\n", x, y, z, add);

    int sum = addition3();
    printf("\nCalling function with No Arguments but Return Value:\n");
    printf("The Addition of 10, 15 and 20 is %d\n", sum);

    return 0;
}

