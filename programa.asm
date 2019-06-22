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
	push 1
mov eax, dword[@DSP+0]
pop ebx
mov dword[eax - 64], ebx
	push 2
mov eax, dword[@DSP+0]
pop ebx
mov dword[eax - 68], ebx
	push dword [ebp - 64]
	push dword [ebp - 68]
	pop eax
	add dword [esp], eax
mov eax, dword[@DSP+0]
pop ebx
mov dword[eax - 72], ebx
	push dword [ebp - 72]
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
