#include "types.h"
#include "stat.h"
#include "user.h"
#define PGSIZE 4096

void a(void *b, void *c){
  printf(1,"test\n");
  printf(1,"address of b:%d, c:%d\n",(int) &b, (int)&c);
  printf(1,"STUPID FUNCTION a() B:%s, C:%s\n",(char *)b, (char *)c);
  exit();
}

int
main(int argc, char *argv[])
{
  void *stack = malloc(2*sizeof(uint)*PGSIZE);

  int pid = clone(&a, (void *)"giggs", (void *)"seventy two", stack);
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
