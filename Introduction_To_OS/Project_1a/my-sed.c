#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void replaceFirstInstance(char *find_term, char *replace_term, char *str, size_t buff){
  int length = strlen(find_term);
  char *ret;
  ret = strstr(str, find_term);
  char *buff_string= ret + length;
  char restofstring[buff];
  if(ret == NULL){
    printf("%s",str );
    exit(0);
  }
  strcpy(restofstring, buff_string);
  if(strlen(ret) > strlen(replace_term)){
    strncpy(ret, replace_term, strlen(ret));
  }
  else{
    strncpy(ret, replace_term, strlen(replace_term));
  }
  strcat(str,restofstring);
  printf("%s",str);
}

int main(int argc, char *argv[]){
  if(argc <3){
    fprintf(stderr, "my-sed: find_term replace_term [file ...]\n");
    exit(1);
  }
  size_t buff = 512;
  char *str = malloc(buff);
  char *find_term = argv[1];
  char *replace_term = argv[2];
  if(argv[3] == NULL){
    fgets(str,buff, stdin);
    replaceFirstInstance(find_term,replace_term,str,buff);
}
else{
  for(int i = 3; i<argc;i++){
    char * filename = argv[i];
    FILE *fp = fopen(filename, "r");
    if (fp == NULL) {
  	printf("cannot open file\n");
    exit(1);
    }
    while (fgets(str,buff, fp) != NULL){
      replaceFirstInstance(find_term,replace_term,str,buff);
    }
    if(feof(fp)){
      fclose(fp);
    }

  }
}

exit(0);
  return 0;
}
