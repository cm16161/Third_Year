#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]){

  if(argc != 2){
    fprintf(stderr, "usage: my-cat <file>\n");
    exit(1);
  }

  char * filename = argv[1];
  FILE *fp = fopen(filename, "r");
  int n = 128;
  char str[n];

  if (fp == NULL) {
	printf("cannot open file\n");
  exit(1);
  }

  while (fgets(str,n, fp) != NULL){
    fprintf(stdout, "%s",str);
  }
  if(feof(fp)){
    fclose(fp);
    exit(0);
  }
  return 0;
}
