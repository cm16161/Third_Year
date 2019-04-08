#include "types.h"
#include "stat.h"
#include "user.h"

int a(void *b, void *c){
  (void) b;
  (void) c;
  printf(1,"USER: IN RANDOM FUNCTION a()\n");
  return 0;
}

int
main(int argc, char *argv[])
{
  clone((void*)a  ((void*) "boob", (void*) "chet"), (void *) "deal", (void*)"extra", (void*)"follow");
  exit();
}
