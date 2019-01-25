#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[]){
  int n = 512;
  char str1[n];
  char str2[n];
  if(argc < 2){
    for(int i = 0; i<25; i++){
      memset(str1,'\0',n);
      fgets(str1,n, stdin);
      if(strcmp(str1,str2) != 0){
        memset(str2,'\0',n);
        fgets(str2,n, stdin);
        if(strcmp(str1,str2) == 0){
          fprintf(stdout, "%s",str1);
        }
        else{
          fprintf(stdout, "%s",str1);
          fprintf(stdout, "%s",str2);
        }
      }
    }
    exit(0);
    }
  for (int i =1; i<argc; i++){

  char *filename = argv[i];
  FILE *fp = fopen(filename, "r");

  if (fp == NULL) {
  printf("my-uniq: cannot open file\n");
  exit(1);
  }

  while (fgets(str1,n, fp) != NULL){
    if(strcmp(str1,str2) != 0){
    if(fgets(str2,n,fp) != NULL){
      if(strcmp(str1,str2) == 0){
        fprintf(stdout, "%s",str1);
      }
      else{
        fprintf(stdout, "%s",str1);
        fprintf(stdout, "%s",str2);
      }
    }
    else{
      fprintf(stdout, "%s",str1);
    }
  }
  }
  if(feof(fp)){
    fclose(fp);
    memset(str1, '\0', n);
    memset(str2, '\0', n);
}
  }
  return 0;
  exit(0);
  }
