#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

char error_message[30] = "An error has occurred\n";
int numberOfArgs = 32;
int historyIndex = 0;

void displayError(){
  write(STDERR_FILENO,error_message,strlen(error_message));
}

void exitHandler(int argNum){
  if(argNum ==0){
    exit(0);
  }
  else{
    displayError();
  }
}

void functionHandler(char* args[numberOfArgs], char* command, int argNum){
  int status;
  char* execute[argNum+2];
  execute[argNum+1]=NULL;
  execute[0]=command;
  if(argNum>0){
    for(int i=1;i<=argNum;i++){
      execute[i] = args[i-1];
    }
  }
  if(fork() == 0){
    execv(execute[0],execute);
  }
  else{
    wait(&status);
  }
}


void cdHandler(char* args[numberOfArgs], int argNum){
  if(argNum != 1){
    displayError();
  }
  else{
    int success = chdir(args[0]);
    if(success != 0){
      displayError();
    }
  }
}

void historyHandler(char* args[numberOfArgs], int argNum, char** history){
  if(argNum >1){
    displayError();
  }
  else{
    if(argNum==0){
      for(int i = 0; i<historyIndex-1;i++){
        printf("%s",history[i]);
      }
    }
    else{
      int n = atoi(args[0]);
      for(int i=historyIndex-1-n;i<historyIndex-1;i++){
        printf("%s",history[i]);
      }
    }
  }
}

void tokenHandler(char* command, char* args[numberOfArgs], int argNum, char* path, char** history){
  if(strcmp(command,"exit") ==0){
    exitHandler(argNum);
  }
  else if(strcmp(command,"cd")==0){
    cdHandler(args, argNum);
  }
  else if(strcmp(command,"history")==0){
    historyHandler(args,argNum,history);
  }
  else{
    char* executable = malloc(strlen(path)+1+strlen(command));
    strcpy(executable,path);
    strcat(executable,command);
    int exec = 0;
    exec = access(executable,X_OK);
    if(exec != 0){
      path = "/usr/bin/";
      free(executable);
      executable= malloc(strlen(path)+1+strlen(command));
      strcpy(executable,path);
      strcat(executable,command);
      exec = access(executable,X_OK);
    }
    if(exec == 0){
      functionHandler(args,executable,argNum);
    }
    
    else{
      displayError();
    }    
  }
  
}


int main(int argc,char* argv[]){
  char* path = "/bin/";
  if(argc>2){
    displayError(); 
    exit(1);
  }
  char *buffer;
  size_t buffsize = 32;
  int eof = 0;
  const char delim[10] =" \n\t";
  char *token;
  char *args[numberOfArgs];
  char *command;
  size_t historySize =1;
  char **history = calloc(historySize, sizeof(char*));
  size_t argLength;
  /* history[0]="TEST\n"; */
  /* history[1]="WORLD\n"; */
  /* history[2]="MOOO\n"; */
  /* history= (char **)malloc(historySize * sizeof(char)); */
  buffer = (char *)malloc(buffsize * sizeof(char));
  if( buffer == NULL)
    {
      perror("Unable to allocate buffer");
      exit(1);
    }
  while(!eof){
    int noInput = 0;
    int count =0;
    printf("wish> ");
    argLength = getline(&buffer, &buffsize, stdin);
    char line[argLength];
    strcpy(line,buffer);
    /* printf("%s",line); */
    token = strtok(buffer,delim);
    if(token==NULL){
      noInput=1;
    }
    if(!noInput){
      command = token;
      history[historyIndex] = strdup(line);
      historyIndex++;
      /* for(int i =0;i<historyIndex;i++){ */
        /* printf("%s",history[i]); */
      /* } */
      /* printf("HISTORYINDEX: %d\n",historyIndex); */

      if(historyIndex == historySize){
        historySize *=2;
        history = (char **) realloc(history, historySize*sizeof(char *));
        /* printf("HISTORYSIZE: %d\n",historySize); */
      }

        while(token != NULL){
        token=strtok(NULL,delim);
        args[count] = token;
        count++;
      }
      count--;
      tokenHandler(command, args,count, path, history);
    }
    eof= feof(stdin);
  }

  
  return 0;
  exit(0);
}

