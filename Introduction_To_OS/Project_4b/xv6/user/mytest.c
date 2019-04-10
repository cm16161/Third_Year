#include "types.h"
#include "stat.h"
#include "user.h"
#define PGSIZE 4096

void a(void *b, void *c){
  printf(1,"TESTESTSET\n");
  printf(1,"USER: IN RANDOM FUNCTION a()\nb:%s\nc:%s\n",(char *) b, (char *)c);
  //exit();
}

int
main(int argc, char *argv[])
{
  void *stack = malloc(sizeof(uint)*PGSIZE);
  int pid = clone(&a, NULL, NULL, stack);
  //int pid = fork();
  //a(NULL,NULL);
  if(pid == 0){
    printf(1,"something....\n");
  }
  else{
    printf(1,"something else....\n");
  }
  exit();
}
