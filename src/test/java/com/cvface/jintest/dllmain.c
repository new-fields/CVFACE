/* Replace "dll.h" with the name of your header */
#include "dll.h"
#include <windows.h>

JNIEXPORT void JNICALL Java_com_cvface_jintest_JavaExcuteC_display(){
	printf("Hello World! I'm From The C Programmer'");
}
