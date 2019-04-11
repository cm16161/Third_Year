#include "types.h"
#include "stat.h"
#include "user.h"
#define PGSIZE 4096

void a(void *b, void *c){
  int mypid = getpid();
  printf(1,"myfdgdfgdnfjkng  pid = %d\n",mypid);
  printf(1,"TESTESTSET\n");
  exit();
}

int
main(int argc, char *argv[])
{
  //void *stack = malloc(2*sizeof(uint)*PGSIZE);
  int pid =fork();// clone(&a, NULL, NULL, stack);
  int mypid = getpid();
  printf(1,"my pid = %d\n",mypid);
  if(pid == 0){
    printf(1,"something....\n");
  }
  else{
    printf(1,"something else....\n");
    sleep(100);
  }
  wait();
  exit();
}
