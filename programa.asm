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

mov eax, dword[@DSP] ;dsp do nivel
sub eax, 4
pop ebx
mov ecx, esp
mov esp, eax
push ebx
mov esp, ecx

	push 2
mov eax, dword[@DSP] ;dsp do nivel
sub eax, 8
pop ebx
mov ecx, esp
mov esp, eax
push ebx
mov esp, ecx

	pop eax
	add dword [esp], eax

mov eax, dword[@DSP] ;dsp do nivel
sub eax, 12
pop ebx
mov ecx, esp
mov esp, eax
push ebx
mov esp, ecx

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
@INTEGER: db '%d', 0
