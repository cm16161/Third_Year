#include "types.h"
#include "stat.h"
#include "user.h"

int
main(int argc, char *argv[])
{
  int k = 12345;
  printf(1, "ADDRK: %p\n", &k);
  int* heaper = malloc(sizeof(int));
  printf(1, "Addr: %p\n", heaper);
  do {
    heaper = malloc(sizeof(int));
    //printf(1, "Addr: %p\n", heaper - sizeof(int));      
  } while (*heaper != -1);
  exit();
}
