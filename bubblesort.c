#include <stdio.h>

void bubblesort(int num[], int n) {
    int temp, i, j;
    for (i = 0; i < n - 1; i++) {
        for (j = 0; j < n - i - 1; j++) {
            if (num[j] > num[j + 1]) {
                temp = num[j];
                num[j] = num[j + 1];
                num[j + 1] = temp;
            }
        }
    }
}

void printArray(int num[], int n) {
    int i;
    printf("Sorted array: ");
    for (i = 0; i < n; i++) {
        printf("%d ", num[i]);
    }
    printf("\n");
}

int main() {
    int num[] = {5, 6, 4, 3, 65, 43, 56, 32, 45};
    int i;
    int size = sizeof(num) / sizeof(num[0]);

    printf("Original array: ");
    for (i = 0; i < size; i++) {
        printf("%d ", num[i]);
    }
    printf("\n");

    bubblesort(num, size);
    printArray(num, size);

    return 0;
} 
