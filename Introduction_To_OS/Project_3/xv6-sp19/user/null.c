#include "types.h"
#include "stat.h"
#include "user.h"

int
main(int argc, char *argv[])
{
  unsigned int* dereferencing = NULL;
  unsigned int* zero = 0;
  
  printf(1, "zero is: %p\n", *zero);
  printf(1, "nullptr is: %p\n", *dereferencing);
  exit();
}
