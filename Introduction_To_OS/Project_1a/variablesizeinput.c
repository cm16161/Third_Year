#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main(int argc, char *argv[])
{
    char buffer[10];
    char *input = 0;
    size_t cur_len = 0;
    FILE *fp = fopen(argv[1], "r");
    while (fgets(buffer, sizeof(buffer), fp) != 0)
    {
        size_t buf_len = strlen(buffer);
        char *extra = realloc(input, buf_len + cur_len + 1);
        if (extra == 0)
            break;
        input = extra;
        strcpy(input + cur_len, buffer);
        cur_len += buf_len;
    }
    printf("%s [%d]", input, (int)strlen(input));
    free(input);
    return 0;
}
