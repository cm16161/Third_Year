#include "types.h"
#include "stat.h"
#include "user.h"

void
opentest(void)
{
  int fd;

  printf(1, "open test\n");
  fd = open("echo", 0);
  if(fd < 0){
    printf(1, "open echo failed!\n");
    exit();
  }
  close(fd);
  fd = open("doesnotexist", 0);
  if(fd >= 0){
    printf(1, "open doesnotexist succeeded!\n");
    exit();
  }
  printf(1, "open test ok\n");
}

int main(void){
  int n=100;
  for(int i = 0; i<n;i++){
    opentest();
  }
  exit();
}
