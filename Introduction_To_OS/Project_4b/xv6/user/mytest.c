#include "types.h"
#include "stat.h"
#include "user.h"
#define PGSIZE 1000

void a(void *b, void *c){
  printf(1,"STUPID FUNCTION a() B:%s, C:%s\n",(char *)b, (char *)c);
  exit();
}

int
main(int argc, char *argv[])
{
  void *stack = malloc(2*sizeof(uint)*PGSIZE);

  int pid = clone(&a, (void *)"hello", (void *)"world", stack);
  if(pid == 0){
    printf(1,"something....\n");
  }
  else{
    printf(1,"something else....\n");
    //sleep(100);
  }
  join(&stack);
  free(stack);
  //wait();
  exit();
}
