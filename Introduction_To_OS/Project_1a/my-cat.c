#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]){

  if(argc < 2){
    exit(0);
  }
  for (int i =1; i<argc; i++){

  char *filename = argv[i];
  FILE *fp = fopen(filename, "r");
  int n = 128;
  char str[n];

  if (fp == NULL) {
	printf("my-cat: cannot open file\n");
  exit(1);
  }

  while (fgets(str,n, fp) != NULL){
    fprintf(stdout, "%s",str);
  }
  if(feof(fp)){
    fclose(fp);
  }
}
  return 0;
  exit(0);
}
