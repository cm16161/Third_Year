#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(){
	char test = "A";
	int test2 = atoi("B");
	printf("Test Value: %d\n",test2);
	char ciphertext[10] = "SEOYKJOEJ";
	char plaintexts[9][26];
	for (int k=0; k<26; k++){
		for(int i=0; i<9;i++){
			int temp = ciphertext[i];
			temp -= 64;
			temp = (temp+k) % 26;
			temp += 64;
			plaintexts[i][k] = temp; 
			//printf("%d\n",temp);
		}
	}
	for (int i=0; i<26; i++){
	printf("Cipher Key of: %d gives plaintext:",i);
		for (int j=0; j<9;j++){
			printf("%c",plaintexts[j][i]);
		}
	printf("\n");
	}
	return 0;
}
