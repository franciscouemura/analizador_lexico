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
sub esp,12
	push 15
mov eax, dword[@DSP+0]
pop ebx
mov dword[eax - 4], ebx
	push 2
mov eax, dword[@DSP+0]
pop ebx
mov dword[eax - 8], ebx
	push dword [ebp - 4]
	push dword [ebp - 8]
	pop ecx
	pop eax
    mov edx,0
	idiv ecx
	push eax
mov eax, dword[@DSP+0]
pop ebx
mov dword[eax - 12], ebx
	push dword [ebp - 12]
	push dword @INTEGER
	call _printf
	add esp, 8
add esp, 12
mov esp,ebp
pop dword[@DSP+8]
pop ebp
ret
section .data
@DSP times 4 db 0
@INTEGER: db '%d' , 0
