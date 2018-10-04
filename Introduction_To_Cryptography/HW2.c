#include <stdio.h>
#include <string.h>


int main(){
	float factor = 0.03846153846;
	float totalProb = 0;
	//float smallestProb[26];
	float P[26] = {2,0,1,0,1,0,0,2,1,1,0,6,2,2,0,0,0,1,0,0,0,1,1,0,2,3};
	
	float P2[26] = {2,0,1,3,5,0,2,2,2,0,0,2,0,3,9,0,0,2,0,1,2,0,1,0,1,0};
	float P3[26] = {2,4,2,0,4,2,4,1,1,3,0,0,0,2,0,2,2,2,0,0,3,0,0,0,3,1};
	float P4[26] = {1,6,2,0,1,0,0,0,3,1,1,1,7,2,1,2,1,0,0,1,1,2,4,0,1,0};
	float P5[26] = {1,0,0,0,6,1,0,0,5,0,1,4,3,0,0,3,0,2,3,0,0,3,0,3,3,0};
	float P6[26] = {2,0,3,1,3,0,0,1,0,0,0,4,2,1,0,6,0,0,2,2,0,0,4,1,2,3};
	float P7[26] = {1,2,0,0,4,0,6,3,0,0,2,3,3,0,0,1,0,1,0,4,0,1,2,3,1,0};
	float P8[26] = {0,2,0,3,0,0,0,0,4,0,1,1,1,2,5,3,1,4,1,0,0,2,1,2,3,1};
	float P9[26] = {2,2,2,3,2,1,3,0,0,0,0,1,6,0,0,1,1,5,1,0,1,0,1,0,5,0};
	float P10[26] = {2,2,1,5,1,0,0,1,2,4,4,2,0,1,2,3,4,0,0,0,0,0,1,0,1,1};
	float maxProb =-10;
	int bestK =0;
	
	float Q[26] = {0.0804,0.0154,0.0306,0.0399,0.1251,0.023,0.0196,0.0549,0.0726,0.0016,0.0067,0.0414,0.0253,0.0709,0.076,0.02,0.0011,0.0612,0.0654,0.0925,0.0271,0.0099,0.0192,0.0019,0.0173,0.0009};
	float totalProbability[26];
	for (int j = 0;j<26;j++){
		P[j] = P[j]*factor;
		P2[j] = P2[j]*factor;
		P3[j] = P3[j]*factor;
		P4[j] = P4[j]*factor;
		P5[j] = P5[j]*factor;
		P6[j] = P6[j]*factor;
		P7[j] = P7[j]*factor;
		P8[j] = P8[j]*factor;
		P9[j] = P9[j]*factor;
		P10[j] = P10[j]*factor;
		totalProb += Q[j];
	}
	for (int k=0;k<26;k++){
		for(int i=0;i<26;i++){
			//printf("i = %d, k= %d , i+k mod 26 = %d\n",i,k, ((i+k)%26));
			//smallestProb[k] += 100000 * ((P[i] - Q[((i+k)%26)])*(P[i] - Q[((i+k)%26)]));
			totalProbability[k]+= (P10[i] * Q[((i+k)%26)]);
	}
	if(maxProb < totalProbability[k]){maxProb = totalProbability[k]; bestK = k;}
	printf("For k=%d : total Probability is: %f \n",k,totalProbability[k]);			
//printf("For k=%d : Probability is: %f \n",k,smallestProb[k]);			
}
	printf("\nFor k=%d : max Probability is %f \n",bestK,maxProb);
	printf("Q = %f\n",totalProb);
	printf("%f\n",Q[4]);
	//printf("Hello, World!\n");
	return 0;
}
