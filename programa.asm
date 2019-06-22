global _main
extern _printf
extern _putchar
extern _scanf
section .text
_main:
push ebp
push dword[@DSP + 0]
mov ebp,esp
mov dword[@DSP +0],ebp
sub esp,24
	push 10
mov eax, dword[@DSP+0]
pop ebx
mov dword[eax - 4], ebx
	push 2
mov eax, dword[@DSP+0]
pop ebx
mov dword[eax - 8], ebx
	push dword [ebp - 4]
	push dword [ebp - 8]
mov eax, dword[@DSP+0]
pop ebx
mov dword[eax - 20], ebx
add esp, 24
mov esp,ebp
pop dword[@DSP+8]
pop ebp
ret
