#include "types.h"
#include "stat.h"
#include "user.h"

int main(void){
  printf(1,"close() syscall has been called: %d times\n", getclosedcount());
  exit();
}
