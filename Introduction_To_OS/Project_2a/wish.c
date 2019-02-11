#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>

char error_message[30] = "An error has occurred\n";
int numberOfArgs = 32;
int historyIndex = 0;
char* path = "/bin/\0";


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

void pathHandler(char* args[numberOfArgs], int argNum){
  if(argNum == 0){
    path = "";
  }
  else{
    int pathSize = argNum-1;
    pathSize*=2;
    for (int i = 0;i<argNum;i++){
      pathSize+= strlen(args[i]);
    }
    char *temp = (char *) malloc(pathSize * sizeof(char));
    strcat(temp,args[0]);
    if(strcmp(temp+strlen(temp)-1,"/")!= 0){
      strcat(temp,"/");
    }
    for(int i = 1;i<argNum;i++){
      strcat(temp,":");
      strcat(temp,args[i]);
      if(strcmp(args[i]+strlen(args[i]-1),"/")!=0){
        strcat(temp,"/");
      }
    }
    path = temp;
  }
}

void redirectHandler(char* command, char* args[numberOfArgs], int argNum){
  if(strcmp(args[argNum-2],">")!= 0){
    displayError();
  }
  else{
    char *filename = args[argNum-1];
    if(access(filename, F_OK) != -1){
      remove(filename);
    }
    FILE *fp = fopen(filename, "w");
    fputs("test\n",fp);
    fclose(fp);
  }
}

void tokenHandler(char* command, char* args[numberOfArgs], int argNum, char** history){
  if(strcmp(command,"exit") ==0){
    exitHandler(argNum);
  }
  else if(strcmp(command,"cd")==0){
    cdHandler(args, argNum);
  }
  else if(strcmp(command,"history")==0){
    historyHandler(args,argNum,history);
  }
  else if(strcmp(command,"path")==0){
    pathHandler(args,argNum);
  }
  else{
    int exec = 0;
    char* tryPath = (char *) malloc(strlen(path)*sizeof(char));
    tryPath = strtok(path,":");
    /* printf("%ld\n",strlen(tryPath)); */
    if(tryPath == NULL){
      displayError();
    }
    else{
      while(tryPath != NULL){
        char* executable = malloc(strlen(tryPath)+1+strlen(command));
        strcpy(executable,tryPath);
        strcat(executable,command);
        exec = access(executable,X_OK);      
        if(exec == 0){
          functionHandler(args,executable,argNum);
          break;
        }
        tryPath = strtok(NULL,":");
      }      
    }
    if(exec != 0){
      displayError();
    }
    
  }
  
}


int main(int argc,char* argv[]){
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
    int redirect = 0;
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
        /* printf("%s\n",token); */
        /* if(strcmp(token,">") == 0){redirect = 1;} */
        args[count] = token;
        count++;
      }
      count--;
      for(int i = 0; i<count;i++){
        if(strcmp(args[i],">")==0){
          redirect = 1;
        }
      }
      if(redirect ==1 ){
        redirectHandler(command,args,count);
      }
      else{
        tokenHandler(command, args,count, history);
      }
    }
    eof= feof(stdin);
  }

  
  return 0;
  exit(0);
}

