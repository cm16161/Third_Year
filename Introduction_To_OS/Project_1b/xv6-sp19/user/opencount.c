#include "types.h"
#include "stat.h"
#include "user.h"

int main(void){
   printf(1,"open() syscall has been called: %d times\n", getopenedcount());
  exit();
}
